package com.bing.lan.core.service

import com.bing.lan.core.domain.Logininfo


interface ILogininfoService {


    fun register(username: String, password: String)

    fun checkUsername(username: String): Boolean

    fun login(username: String, password: String): Logininfo


}