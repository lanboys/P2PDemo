package com.bing.lan.core.base.domain

import lombok.Getter
import lombok.Setter
import org.apache.ibatis.type.Alias
import java.util.*

/**
 * Created by 蓝兵 on 2018/5/9.
 *
 *
 * 登陆日志
 */
@Getter
@Setter
@Alias("IpLog")
open class IpLog : BaseDomain {

    public var username: String = ""
    public var loginTime: Date? = null
    public var ip: String = ""

    var loginState = LOGINSTATE_FAILD
    var loginType: Int = 0
    var loginInfoId: Long? = null

    val displayState: String
        get() = if (this.loginState == LOGINSTATE_FAILD) "登录失败" else "登录成功"

    constructor() : super() {}

    constructor(username: String, loginTime: Date, ip: String, loginType: Int,
                loginInfoId: Long?) : super() {
        this.username = username
        this.loginTime = loginTime
        this.ip = ip
        this.loginState = IpLog.LOGINSTATE_FAILD
        this.loginType = loginType
        this.loginInfoId = loginInfoId
    }

    companion object {

        var LOGINSTATE_FAILD = 0//登陆失败
        var LOGINSTATE_SUCCESS = 1//登陆成功
    }
}
