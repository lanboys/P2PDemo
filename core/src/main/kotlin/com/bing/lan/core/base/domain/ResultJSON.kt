package com.bing.lan.core.base.domain

/**
 * Created by 蓝兵 on 2018/5/3.
 */
open class ResultJSON() {

    var success: Boolean = false

    var msg: String? = null

    var data: Any? = null


    constructor(success: Boolean, msg: String) : this() {
        this.success = success
        this.msg = msg
    }

    constructor(msg: String) : this() {
        this.success = success
        this.msg = msg
    }
}