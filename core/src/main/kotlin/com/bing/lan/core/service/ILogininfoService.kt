package com.bing.lan.core.service


interface ILogininfoService {


    fun register(username: String, password: String)

    fun checkUsername(username: String): Boolean


}