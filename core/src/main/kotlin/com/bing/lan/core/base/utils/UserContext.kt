package com.bing.lan.core.base.utils

import com.bing.lan.core.base.domain.Logininfo
import com.bing.lan.core.business.service.VerifyCode
import org.springframework.web.context.request.RequestContextHolder
import org.springframework.web.context.request.ServletRequestAttributes
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpSession


/**
 * Created by 蓝兵 on 2018/5/6.
 */

object UserContext {

     public val LOGIN_IN_SESSION = "logininfo_session"
    public val VERIFYCODE_IN_SESSION = "VERIFYCODE_IN_SESSION"

    private val session: HttpSession
        get() = httpServletRequest.session

    private val httpServletRequest: HttpServletRequest
        get() = (RequestContextHolder
                .getRequestAttributes() as ServletRequestAttributes).request


    fun putLogininfo(logininfo: Logininfo) {
        session.setAttribute(LOGIN_IN_SESSION, logininfo)
    }

    fun getLogininfo(): Logininfo {
        return session.getAttribute(LOGIN_IN_SESSION) as Logininfo
    }

    fun putVerifyCode(code: VerifyCode) {
        session.setAttribute(VERIFYCODE_IN_SESSION, code)
    }

    fun getVerifyCode(): VerifyCode {
        return session.getAttribute(VERIFYCODE_IN_SESSION) as VerifyCode
    }
}