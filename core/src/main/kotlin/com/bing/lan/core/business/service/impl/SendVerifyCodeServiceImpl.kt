package com.bing.lan.core.business.service.impl

import com.bing.lan.core.base.ServiceRuntimeException
import com.bing.lan.core.base.utils.DateUtil
import com.bing.lan.core.base.utils.UserContext
import com.bing.lan.core.business.service.ISendVerifyCodeService
import com.bing.lan.core.business.service.VerifyCode
import okhttp3.FormBody
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import org.springframework.stereotype.Service
import java.util.*
import java.util.UUID


/**
 * Created by 蓝兵 on 2018/5/7.
 */

@Service
open class SendVerifyCodeServiceImpl : ISendVerifyCodeService {


//    @Value("${m5c.url}")
//    lateinit var url: String


    override fun verifyCode(phoneNumber: String, verifyCode: String) {
        val vc = UserContext.getVerifyCode()
        val ret = (vc != null && vc.phoneNumber == phoneNumber
                && vc.randomCode == verifyCode
                && DateUtil.getSecondsBetweenDates(vc.lastSendTime, Date()) <= 60 * 3)
        if (!ret) {
            throw ServiceRuntimeException("验证码不正确")
        }
    }

    override fun sendVerifyCode(phoneNumber: String) {
        if (checkUserCanSendVerifyCode()) {
            val randomCode = UUID.randomUUID().toString().substring(0, 4)
            val sb = StringBuilder(100).append("您的手机验证码为:")
                    .append(randomCode).append(",请在3分钟之内输入有效!")
            val vc = VerifyCode(phoneNumber, randomCode, Date(), sb.toString())
            sendMessage(vc)
        } else {
            throw ServiceRuntimeException("你发送短信的频率太高")
        }
    }

    /**
     * 5 秒可以重新发送
     */
    private fun checkUserCanSendVerifyCode(): Boolean {
        val vc = UserContext.getVerifyCode()
        return vc == null || DateUtil.getSecondsBetweenDates(
                vc.lastSendTime, Date()) > 5L
    }

    private fun sendMessage(code: VerifyCode) {

        val host = "http://localhost:8085"
        val sb = StringBuilder().append("username=").append("lan")
                .append("&password=").append("bing")
                .append("&apiKey=").append("apiKey")
                .append("&mobile=").append(code.phoneNumber)
                .append("&code=").append(code.randomCode)
                .append("&content=").append(code.content)

//        val urlConnection = URL(host).openConnection() as HttpURLConnection
//        urlConnection.requestMethod = "POST"
//        urlConnection.doOutput = true
//        urlConnection.outputStream.write(sb.toString().toByteArray())
//        val inputStream = urlConnection.inputStream
//        val responseText = StreamUtils.copyToString(inputStream, Charset.forName("UTF-8"))
//        print("发送短信返回数据：" + responseText)
//        if (urlConnection.responseCode != 200) {
//            throw ServiceRuntimeException("短信发送失败")
//        }

        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)

        val okHttpClient = OkHttpClient
                .Builder().addInterceptor(loggingInterceptor)
                .build()
        val builder = Request.Builder()

//        //GET
//        val request = builder
//                .get()
//                .url(host+"?" + sb.toString())
//                .build()

        //POST
        val formBody = FormBody.Builder()
                .add("username", "lan")
                .add("password", "bing")
                .add("apiKey", "apiKey")
                .add("mobile", code.phoneNumber)
                .add("code", code.randomCode)
                .add("content", code.content)
                .build()

        val request = builder
                .post(formBody)
                .url(host)
                .build()

        val newCall = okHttpClient.newCall(request)
        val response = newCall.execute()
        if (!response.isSuccessful) {
            throw ServiceRuntimeException("短信网关服务异常")
        } else {
            println(code)
            UserContext.putVerifyCode(code)
        }

//        newCall.enqueue(object : Callback {
//            override fun onFailure(call: Call, e: IOException) {
//                print("请求失败")
//            }
//
//            @Throws(IOException::class)
//            override fun onResponse(call: Call, response: Response) {
//                print("请求成功")
//            }
//        })
    }
}


