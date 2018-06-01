package com.bing.lan.web.domian

/**
 * Created by 蓝兵 on 2018/4/27.
 */

class User(var name: String?, var age: Int) {

    override fun toString(): String {
        return "User{" +
                "name='" + name + '\''.toString() +
                ", age=" + age +
                '}'.toString()
    }
}
