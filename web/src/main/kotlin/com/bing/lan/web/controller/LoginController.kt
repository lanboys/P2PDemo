package com.bing.lan.web.controller;

import com.bing.lan.core.base.domain.Logininfo
import com.bing.lan.core.base.domain.ResultJSON
import com.bing.lan.core.base.service.ILogininfoService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody
import javax.servlet.http.HttpServletRequest

/**
 * Created by 蓝兵 on 2018/5/3.
 */


@Controller
open class LoginController : BaseController() {


    @Autowired
    lateinit var logininfoService: ILogininfoService

    @RequestMapping("/login")
    @ResponseBody
    fun login(username: String, password: String, request: HttpServletRequest): ResultJSON {
        val resultJSON = ResultJSON()
        try {
            val login = logininfoService.login(username, password,
                    Logininfo.USERTYPE_NORMAL, request.remoteAddr)

            resultJSON.success = true
            resultJSON.data = login
        } catch (e: Throwable) {
            resultJSON.success = false
            resultJSON.msg = e.localizedMessage
        }
        return resultJSON
    }


}
