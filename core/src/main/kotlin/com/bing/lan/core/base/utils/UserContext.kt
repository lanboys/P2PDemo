package com.bing.lan.core.base.utils

import com.bing.lan.core.base.domain.Logininfo
import org.springframework.web.context.request.RequestContextHolder
import org.springframework.web.context.request.ServletRequestAttributes
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpSession

/**
 * Created by 蓝兵 on 2018/5/6.
 */

internal object UserContext {

    val LOGIN_IN_SESSION = "loginingo"

    private val session: HttpSession
        get() = httpServletRequest.session

    private val httpServletRequest: HttpServletRequest
        get() = (RequestContextHolder
                .getRequestAttributes() as ServletRequestAttributes).request


    fun putLogininfo(logininfo: Logininfo) {
        session.setAttribute(LOGIN_IN_SESSION, logininfo)
    }

    fun getLogininfo() {
        session.getAttribute(LOGIN_IN_SESSION)
    }
}