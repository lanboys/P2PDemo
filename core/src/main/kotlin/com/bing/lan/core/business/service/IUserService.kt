package com.bing.lan.core.business.service

import com.bing.lan.core.base.domain.Userinfo

/**
 * Created by 蓝兵 on 2018/5/7.
 */

interface IUserService {

    fun update(userinfo: Userinfo)

    operator fun get(id: Long): Userinfo

    fun bindPhone(phoneNumber: String, verifyCode: String): Boolean

    /**
     * 修改基本信息
     * @param userinfo
     */
    fun updateBasicInfo(userinfo: Userinfo)
}
