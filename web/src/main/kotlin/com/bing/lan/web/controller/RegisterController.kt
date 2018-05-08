package com.bing.lan.web.controller;

import com.bing.lan.core.base.domain.Logininfo
import com.bing.lan.core.base.service.ILogininfoService
import com.bing.lan.core.domain.ResultJSON
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody

/**
 * Created by 蓝兵 on 2018/5/3.
 */


@Controller
open class RegisterController : BaseController() {


    @Autowired
    lateinit var logininfoService: ILogininfoService

    @RequestMapping("/register")
    @ResponseBody
    fun register(username: String, password: String): ResultJSON {
        val resultJSON = ResultJSON()
        try {
            logininfoService.register(username, password)
            resultJSON.success = true
        } catch (e: Exception) {
            resultJSON.success = false
            resultJSON.msg = e.localizedMessage
        }
        return resultJSON
    }


    @RequestMapping("/checkUsername")
    @ResponseBody
    fun checkUsername(username: String): ResultJSON {
        val resultJSON = ResultJSON()
        resultJSON.success = logininfoService.checkUsername(username, Logininfo.USERTYPE_NORMAL)
        resultJSON.msg = "新用户可注册"
        if (resultJSON.success) {
            resultJSON.msg = "用户已经存在"
        }
        return resultJSON
    }

}
