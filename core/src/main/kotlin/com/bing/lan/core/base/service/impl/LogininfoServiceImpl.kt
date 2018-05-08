package com.bing.lan.core.base.service.impl

import com.bing.lan.core.base.ServiceRuntimeException
import com.bing.lan.core.base.domain.Account
import com.bing.lan.core.base.domain.Logininfo
import com.bing.lan.core.base.domain.Userinfo
import com.bing.lan.core.base.mapper.AccountMapper
import com.bing.lan.core.base.mapper.LogininfoMapper
import com.bing.lan.core.base.mapper.UserinfoMapper
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
    lateinit var userinfoMapper: UserinfoMapper

    @Autowired
    lateinit var accountMapper: AccountMapper


    @Autowired
    lateinit var logininfoMapper: LogininfoMapper

    override fun register(username: String, password: String) {
        if (checkUsername(username, Logininfo.USERTYPE_NORMAL)) {
            throw ServiceRuntimeException("用户名已经存在!!")
        }
        val logininfo = Logininfo()
        logininfo.password = password
        logininfo.username = username
        logininfo.userType = Logininfo.USERTYPE_NORMAL
        logininfoMapper.insert(logininfo)

        val account = Account.empty(logininfo.id)
        accountMapper.insert(account)

        val userinfo = Userinfo.empty(logininfo.id)
        userinfoMapper.insert(userinfo)
    }


    override fun checkUsername(username: String, userType: Int): Boolean {
        val count = logininfoMapper.getCountByUsername(username,userType)
        return count > 0
    }

    override fun login(username: String, password: String, userType: Int): Logininfo {

        val userInfo: Logininfo = logininfoMapper.login(username, password,userType)
                ?: throw ServiceRuntimeException("用户名或者密码错误！！")

        UserContext.putLogininfo(userInfo)
        return userInfo
    }
}
