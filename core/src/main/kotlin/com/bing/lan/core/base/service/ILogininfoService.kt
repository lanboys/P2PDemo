package com.bing.lan.core.base.service

import com.bing.lan.core.base.domain.Logininfo


interface ILogininfoService {


    /**
     * 前端用户 注册
     */
    fun register(username: String, password: String)


    /**
     * 检查是否有重复的用户名
     *
     * @return true 存在用户名
     */
    fun checkUsername(username: String, userType: Int): Boolean


    /**
     * 登录
     */
    fun login(username: String, password: String, userType: Int, ip: String): Logininfo


    /**
     * 是否有管理员
     */
    fun hasAdmin(): Boolean

    /**
     * 创建默认的管理员
     */
    fun createDefaultAdmin()

}