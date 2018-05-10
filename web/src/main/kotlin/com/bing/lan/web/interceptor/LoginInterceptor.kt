package com.bing.lan.web.interceptor

import com.bing.lan.core.base.utils.UserContext
import com.bing.lan.web.annotation.RequiredLogin
import org.springframework.web.method.HandlerMethod
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

/**
 * Created by 蓝兵 on 2018/5/10.
 */
open class LoginInterceptor : HandlerInterceptorAdapter() {

    override fun preHandle(request: HttpServletRequest, response: HttpServletResponse, handler: Any): Boolean {

        if (handler is HandlerMethod) {
            val methodAnnotation = handler.getMethodAnnotation(RequiredLogin::class.java)

            methodAnnotation?.let {
                if (request.session.getAttribute(UserContext.LOGIN_IN_SESSION) == null) {
                    response.sendRedirect("/login.html")
                    println("==========未登录，被拦截=========")
                    return false
                }
            }
        }
        return super.preHandle(request, response, handler)
    }
}





