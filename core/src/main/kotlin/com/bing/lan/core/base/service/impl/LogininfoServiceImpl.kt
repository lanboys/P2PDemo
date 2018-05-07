package com.bing.lan.core.base.service.impl

import com.bing.lan.core.base.ServiceRuntimeException
import com.bing.lan.core.base.domain.Logininfo
import com.bing.lan.core.base.mapper.LogininfoMapper
import com.bing.lan.core.base.service.ILogininfoService
import com.bing.lan.core.base.utils.UserContext
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

/**
 * Created by 蓝兵 on 2018/5/3.
 */

@Service("logininfoService")
open class LogininfoServiceImpl : ILogininfoService {


    @Autowired
    lateinit var logininfoMapper: LogininfoMapper

    override fun register(username: String, password: String) {
        if (checkUsername(username)) {
            throw ServiceRuntimeException("用户已经存在!!")
        }
        val logininfo = Logininfo()
        logininfo.password = password
        logininfo.username = username
        logininfoMapper.insert(logininfo)
    }


    override fun checkUsername(username: String): Boolean {
        val count = logininfoMapper.getCountByUsername(username)
        return count > 0
    }

    override fun login(username: String, password: String): Logininfo {

        val userInfo: Logininfo = logininfoMapper.login(username, password)
                ?: throw ServiceRuntimeException("用户名或者密码错误！！")

        UserContext.putLogininfo(userInfo)
        return userInfo
    }
}
