package com.bing.lan.web.controller;

import com.bing.lan.core.base.utils.UserContext
import com.bing.lan.core.business.service.IAccountService
import com.bing.lan.core.business.service.IUserService
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
    lateinit var userinfoService: IUserService


    @Autowired
    lateinit var accountService: IAccountService

    @RequestMapping("/personal")
    fun personal(model: Model): String {

        val logininfo = UserContext.getLogininfo()
        logininfo.id?.let { id ->
            model.addAttribute("userinfo", userinfoService.get(id))
            model.addAttribute("account", accountService.get(id))
        }
        return "personal"
    }


}
