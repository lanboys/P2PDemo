package com.bing.lan.core.business.service

/**
 * Created by 蓝兵 on 2018/5/7.
 */
/**
 * 短信验证码相关服务
 * @author Administrator
 */
interface ISendVerifyCodeService {

    fun sendVerifyCode(phoneNumber: String)

    /**
     * 短信验证码校验
     * @param phoneNumber
     * @param verifyCode
     */
    fun verifyCode(phoneNumber: String, verifyCode: String): Boolean
}
