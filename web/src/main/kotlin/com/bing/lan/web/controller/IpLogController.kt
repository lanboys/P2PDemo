package com.bing.lan.web.controller;

import com.bing.lan.core.base.domain.Logininfo
import com.bing.lan.core.base.query.IpLogQueryObject
import com.bing.lan.core.base.service.IIpLogService
import com.bing.lan.core.base.utils.UserContext
import com.bing.lan.web.annotation.RequiredLogin
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.RequestMapping

/**
 * Created by 蓝兵 on 2018/5/3.
 */

@Controller
open class IpLogController : BaseController() {

    @Autowired
    lateinit var ipLogService: IIpLogService

    @RequiredLogin
    @RequestMapping("/iplog")//  'qo' 在前端页面中能直接拿到
    fun ipLog(@ModelAttribute("qo") queryObject: IpLogQueryObject, model: Model): String {

        queryObject.username = UserContext.getLogininfo().getUsername()
        queryObject.isLike = false
        queryObject.userType = Logininfo.USERTYPE_NORMAL

        val query = ipLogService.query(queryObject)

        model.addAttribute("pageResult", query)

        return "iplog_list"
    }
}
