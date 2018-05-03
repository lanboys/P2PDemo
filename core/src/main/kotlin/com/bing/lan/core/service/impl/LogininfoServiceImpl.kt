package com.bing.lan.core.service

import com.bing.lan.core.ServiceRuntimeException
import com.bing.lan.core.domain.Logininfo
import com.bing.lan.core.mapper.LogininfoMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

/**
 * Created by 蓝兵 on 2018/5/3.
 */

@Service("logininfoService")
open class LogininfoServiceImpl : ILogininfoService {

    @Autowired(required = false)
    var logininfoMapper: LogininfoMapper? = null

    override fun register(username: String, password: String) {
        val count = logininfoMapper!!.getCountByUsername(username)
        if (count <= 0) {
            val logininfo = Logininfo()
            logininfo.password = password
            logininfo.username = username
            logininfoMapper!!.insert(logininfo)
        } else {
            throw ServiceRuntimeException("用户已经存在!!")
        }
    }
}
