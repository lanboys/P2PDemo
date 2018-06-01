package com.bing.lan.core.business.service

/**
 * Created by 蓝兵 on 2018/6/1.
 */
interface IEmailActiveService {

    /**
     * 发送激活邮件
     * @param email
     */
    fun sendActiveEmail(email: String)

    /**
     * 绑定邮箱验证
     * @param code
     * @return
     */
    fun bindEmail(code: String)
}
