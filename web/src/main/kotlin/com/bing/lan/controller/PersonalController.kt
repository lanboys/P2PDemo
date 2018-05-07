package com.bing.lan.controller;

import com.bing.lan.core.base.service.ILogininfoService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.RequestMapping

/**
 * Created by 蓝兵 on 2018/5/3.
 */


@Controller
open class PersonalController : BaseController() {


    @Autowired
    lateinit var logininfoService: ILogininfoService

    @RequestMapping("/personal")
    fun register(model: Model): String {




    }


}
