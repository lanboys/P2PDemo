package com.bing.lan.web.controller;

import com.bing.lan.core.base.utils.UserContext
import com.bing.lan.core.business.service.IAccountService
import com.bing.lan.core.business.service.IUserService
import com.bing.lan.core.domain.ResultJSON
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


    @RequiredLogin
    @RequestMapping("/personal")
    fun personal(model: Model): String {

        val logininfo = UserContext.getLogininfo()
        logininfo.id?.let { id ->
            model.addAttribute("userinfo", userinfoService.get(id))
            model.addAttribute("account", accountService.get(id))
        }
        return "personal"
    }



    @RequiredLogin
    @RequestMapping("/sendVerifyCode")
    @ResponseBody
    fun sendVerifyCode(model: Model): ResultJSON {

        println("发送验证码成功>>>>>>>>")
        val resultJSON = ResultJSON()
        resultJSON.success=true
        resultJSON.msg="发送成功"
        resultJSON.data="验证码为 000000"
        return resultJSON
    }


}
