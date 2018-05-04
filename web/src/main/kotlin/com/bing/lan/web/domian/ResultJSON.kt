package com.bing.lan.web.domian

/**
 * Created by 蓝兵 on 2018/5/3.
 */
open class ResultJSON() {

    var success: Boolean = false

    var msg: String? = null


    constructor(success: Boolean, msg: String) : this() {
        this.success = success
        this.msg = msg
    }

    constructor(msg: String) : this() {
        this.success = success
        this.msg = msg
    }
}