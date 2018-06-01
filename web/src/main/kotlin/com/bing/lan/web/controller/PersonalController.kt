package com.bing.lan.web.controller;

import com.bing.lan.core.base.domain.ResultJSON
import com.bing.lan.core.base.utils.UserContext
import com.bing.lan.core.business.service.IAccountService
import com.bing.lan.core.business.service.IEmailActiveService
import com.bing.lan.core.business.service.ISendVerifyCodeService
import com.bing.lan.core.business.service.IUserService
import com.bing.lan.web.annotation.RequiredLogin
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody

/**
 * Created by 蓝兵 on 2018/5/3.
 */


@Controller
open class PersonalController : BaseController() {


    @Autowired
    lateinit var userinfoService: IUserService

    @Autowired
    lateinit var accountService: IAccountService

    @Autowired
    lateinit var sendVerifyCodeService: ISendVerifyCodeService

    @Autowired
    lateinit var emailActiveService: IEmailActiveService

    @RequiredLogin
    @RequestMapping("/personal")
    fun personal(model: Model): String {

        val logininfo = UserContext.getLogininfo()
        logininfo!!.id.let { it ->
            val userinfo = userinfoService.getUserinfo(it)
            val isBindPhone = userinfo.isBindPhone
            val isBindEmail = userinfo.isBindEmail
            val realAuth = userinfo.realAuth

            model.addAttribute("realAuth", realAuth)
            model.addAttribute("isBindPhone", isBindPhone)
            model.addAttribute("isBindEmail", isBindEmail)
            model.addAttribute("userinfo", userinfo)
            model.addAttribute("account", accountService.getAccount(it))
        }
        return "personal"
    }


    @RequiredLogin
    @RequestMapping("/sendVerifyCode")
    @ResponseBody
    fun sendVerifyCode(phoneNumber: String): ResultJSON {
        val resultJSON = ResultJSON()
        try {
            sendVerifyCodeService.sendVerifyCode(phoneNumber)
            resultJSON.success = true
            resultJSON.msg = "发送成功"
        } catch (e: Exception) {
            resultJSON.success = false
            resultJSON.msg = "发送失败：" + e.localizedMessage
            println("发送验证码>>>>>>>>失败: " + e.localizedMessage)
        }
        return resultJSON
    }

    @RequiredLogin
    @RequestMapping("/bindPhone")
    @ResponseBody
    fun bindPhone(phoneNumber: String, verifyCode: String): ResultJSON {
        val resultJSON = ResultJSON()
        try {
            userinfoService.bindPhone(phoneNumber, verifyCode)
            resultJSON.success = true
            resultJSON.msg = "绑定成功"
        } catch (e: Exception) {
            resultJSON.success = false
            resultJSON.msg = "绑定失败：" + e.localizedMessage
        }
        return resultJSON
    }

    @RequiredLogin
    @RequestMapping("/bindEmail")
    @ResponseBody
    fun bindEmail(email: String): ResultJSON {
        val resultJSON = ResultJSON()
        try {
            emailActiveService.sendActiveEmail(email)
            resultJSON.success = true
        } catch (e: Exception) {
            resultJSON.success = false
            resultJSON.msg = "发送失败：" + e.localizedMessage
        }
        return resultJSON
    }

    @RequestMapping("/checkMailActive")
    fun checkMailActive(code: String, model: Model): String {
        try {
            emailActiveService.bindEmail(code)
            model.addAttribute("success", true)
        } catch (e: Exception) {
            model.addAttribute("success", false)
            model.addAttribute("msg", e.message)
        }

        return "checkmail_result"
    }
}
