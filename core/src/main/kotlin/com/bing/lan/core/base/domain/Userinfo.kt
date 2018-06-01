package com.bing.lan.core.base.domain

import com.bing.lan.core.base.utils.BitStatesUtils
import lombok.Getter
import lombok.Setter
import org.apache.ibatis.type.Alias
import org.springframework.util.StringUtils

/**
 * Created by 蓝兵 on 2018/6/1.
 */

@Getter
@Setter
@Alias("Userinfo")
class Userinfo : BaseDomain() {
    var version: Int = 0// 版本号
    var bitState: Long? = 0L // 位状态
    var realName: String? = null // 对应实名认证中的真实姓名
    var idNumber: String? = null // 对应实名认证中的身份证号
    var email: String? = null // 用户邮箱
    var phoneNumber = "" // 手机号
    var authScore = 0//用户当前认证分数
    var realauthId: Long? = null   //用户有效的实名认证对象

    // ====================== 会员基本资料 ===================
    var incomeGrade: SystemDictionaryItem? = null // 月收入
    var marriage: SystemDictionaryItem? = null // 婚姻情况
    var kidCount: SystemDictionaryItem? = null // 子女情况
    var educationBackground: SystemDictionaryItem? = null // 学历
    var houseCondition: SystemDictionaryItem? = null // 住房条件

    val isBindPhone: Boolean
        get() = BitStatesUtils.hasState(bitState!!, BitStatesUtils.OP_BIND_PHONE)

    val isBindEmail: Boolean
        get() = BitStatesUtils.hasState(bitState!!, BitStatesUtils.OP_BIND_EMAIL)

    val baseInfo: Boolean
        get() = BitStatesUtils.hasState(bitState!!, BitStatesUtils.OP_BASE_INFO)

    val realAuth: Boolean
        get() = BitStatesUtils.hasState(bitState!!, BitStatesUtils.OP_REAL_AUTH)

    val vedioAuth: Boolean
        get() = BitStatesUtils.hasState(bitState!!, BitStatesUtils.OP_VEDIO_AUTH)

    /**
     * 获取用户真实名字的隐藏字符串，只显示姓氏
     *
     * @param realName 真实名字
     * @return
     */
    val anonymousRealName: String?
        get() {
            if (StringUtils.hasLength(realName)) {
                val len = realName!!.length
                var replace = ""
                replace += realName!![0]
                for (i in 1 until len) {
                    replace += "*"
                }
                return replace
            }
            return realName
        }

    fun addState(state: Long?) {
        this.bitState = BitStatesUtils.addState(this.bitState!!, state!!)
    }

    companion object {

        val serialVersionUID = -2194938919842714855L

        fun empty(id: Long ): Userinfo {
            val userinfo = Userinfo()
            userinfo.id = id
            userinfo.bitState = BitStatesUtils.OP_BASIC_INFO
            return userinfo
        }
    }
}