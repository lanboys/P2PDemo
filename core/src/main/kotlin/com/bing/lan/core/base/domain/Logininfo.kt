package com.bing.lan.core.base.domain

import lombok.Getter
import lombok.Setter
import org.apache.ibatis.type.Alias

/**
 * Created by 蓝兵 on 2018/6/1.
 */

@Getter
@Setter
@Alias("Logininfo")
open class Logininfo : BaseDomain() {

    var username: String? = null
    var password: String? = null
    var state = STATE_NORMAL

    var userType: Int = 0//用户类型
    var admin = false

    companion object {

        val STATE_NORMAL = 0
        val STATE_LOCK = 1
        val STATE_DELETE = -1

        val USERTYPE_NORMAL = 0//前段用户
        val USERTYPE_SYSTEM = 1//后台用户
    }
}
