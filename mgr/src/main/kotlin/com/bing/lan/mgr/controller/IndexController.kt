package com.bing.lan.mgr.controller;

import com.bing.lan.core.base.service.IIpLogService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping

/**
 * Created by 蓝兵 on 2018/5/3.
 */

@Controller
open class IndexController : BaseController() {

    @Autowired
    lateinit var ipLogService: IIpLogService

    @RequestMapping("/index")
    fun main(): String {
        return "main"
    }
}
