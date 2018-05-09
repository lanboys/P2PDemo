package com.bing.lan.core.base.service.impl

import com.bing.lan.core.base.ServiceRuntimeException
import com.bing.lan.core.base.domain.Account
import com.bing.lan.core.base.domain.IpLog
import com.bing.lan.core.base.domain.Logininfo
import com.bing.lan.core.base.domain.Userinfo
import com.bing.lan.core.base.mapper.AccountMapper
import com.bing.lan.core.base.mapper.IpLogMapper
import com.bing.lan.core.base.mapper.LogininfoMapper
import com.bing.lan.core.base.mapper.UserinfoMapper
import com.bing.lan.core.base.service.ILogininfoService
import com.bing.lan.core.base.utils.BidConst
import com.bing.lan.core.base.utils.UserContext
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*


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


    @Autowired
    lateinit var ipLogMapper: IpLogMapper

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
        val count = logininfoMapper.getCountByUsername(username, userType)
        return count > 0
    }

    override fun login(username: String, password: String, userType: Int, ip: String): Logininfo {
        val ipLog = IpLog(username, Date(), ip, userType, null)

        val userInfo: Logininfo? = logininfoMapper.login(username, password, userType)
        if (userInfo != null) {
            UserContext.putLogininfo(userInfo)
            ipLog.loginInfoId = userInfo.id
            ipLog.loginState = IpLog.LOGINSTATE_SUCCESS
            ipLogMapper.insert(ipLog)
            return userInfo
        } else {
            ipLogMapper.insert(ipLog)
            throw ServiceRuntimeException("用户名或者密码错误!!")
        }

    }

    override fun hasAdmin(): Boolean {
        return logininfoMapper.getCountByUsername(BidConst.DEFAULT_ADMIN_NAME, Logininfo.USERTYPE_SYSTEM) > 0
    }

    override fun createDefaultAdmin() {
        val logininfo = Logininfo()
        logininfo.password = BidConst.DEFAULT_ADMIN_PASSWORD
        logininfo.username = BidConst.DEFAULT_ADMIN_NAME
        logininfo.userType = Logininfo.USERTYPE_SYSTEM
        logininfo.admin = true

        logininfoMapper.insert(logininfo)
    }
}
