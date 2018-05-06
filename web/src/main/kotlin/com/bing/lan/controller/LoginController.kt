package com.bing.lan.controller;

import com.bing.lan.core.service.ILogininfoService
import com.bing.lan.web.domian.ResultJSON
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody

/**
 * Created by 蓝兵 on 2018/5/3.
 */


@Controller
open class LoginController : BaseController() {


    @Autowired
    lateinit var logininfoService: ILogininfoService

    @RequestMapping("/login")
    @ResponseBody
    fun register(username: String, password: String): ResultJSON {
        val resultJSON = ResultJSON()
        try {
            val login = logininfoService.login(username, password)
            resultJSON.success = true
            resultJSON.data = login
        } catch (e: Throwable) {
            resultJSON.success = false
            resultJSON.msg = e.localizedMessage
        }
        return resultJSON
    }


}
