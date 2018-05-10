package com.bing.lan.core.business.service.impl

import com.bing.lan.core.base.ServiceRuntimeException
import com.bing.lan.core.business.service.ISendVerifyCodeService
import com.bing.lan.core.business.service.VerifyCode
import okhttp3.FormBody
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import org.springframework.stereotype.Service


/**
 * Created by 蓝兵 on 2018/5/7.
 */

@Service
open class SendVerifyCodeServiceImpl : ISendVerifyCodeService {


//    @Value("${m5c.url}")
//    lateinit var url: String


    override fun verifyCode(phoneNumber: String, verifyCode: String): Boolean {
        return false
    }

    override fun sendVerifyCode(phoneNumber: String) {
        val host = "http://localhost:8085"
        val sb = StringBuilder().append("username=").append("lan")
                .append("&password=").append("bing")
                .append("&apiKey=").append("apiKey")
                .append("&mobile=").append("110")
                .append("&content=").append("我是p2p平台发送的短信")

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
                .Builder().
                addInterceptor(loggingInterceptor)
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
                .add("mobile", "110")
                .add("content", "我是p2p平台发送的短信")
                .build()

        val request = builder
                .post(formBody)
                .url(host)
                .build()

        val newCall = okHttpClient.newCall(request)
        val response = newCall.execute()
        if (!response.isSuccessful) {
            throw ServiceRuntimeException("短信发送失败")
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

    private fun checkUserCanSendVerifyCode(): Boolean {
        return false
    }

    private fun sendMessage(code: VerifyCode) {

    }
}


