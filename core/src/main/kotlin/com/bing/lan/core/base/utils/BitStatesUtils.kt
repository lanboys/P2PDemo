package com.bing.lan.core.base.utils

/**
 * 用户状态类，记录用户在平台使用系统中所有的状态。
 *
 * @author Administrator
 */
object BitStatesUtils {

    val OP_BASIC_INFO = 1L //用户注册成功的标示,及为默认初始状态
    val OP_BIND_PHONE: Long = 2L shl 0 //用户绑定手机状态码
    val OP_BIND_EMAIL: Long = 2L shl 1//用户绑定邮箱
    val OP_BASE_INFO: Long  = 2L shl 2//填写基本资料
    val OP_REAL_AUTH: Long  = 2L shl 3//用户实名认证
    val OP_VEDIO_AUTH: Long = 2L shl 4//视频认证

    /**
     * @param states 所有状态值
     * @param value  需要判断状态值
     * @return 是否存在
     */
    fun hasState(states: Long, value: Long): Boolean {
        return states and value != 0L
    }

    /**
     * @param states 已有状态值
     * @param value  需要添加状态值
     * @return 新的状态值
     */
    fun addState(states: Long, value: Long): Long {
        return if (hasState(states, value)) {
            states
        } else states or value
    }

    /**
     * @param states 已有状态值
     * @param value  需要删除状态值
     * @return 新的状态值
     */
    fun removeState(states: Long, value: Long): Long {
        return if (!hasState(states, value)) {
            states
        } else states xor value
    }
}
