package com.bing.lan.core.business.service.impl

import com.bing.lan.core.business.service.ISendVerifyCodeService
import com.bing.lan.core.business.service.VerifyCode
import org.springframework.stereotype.Service

/**
 * Created by 蓝兵 on 2018/5/7.
 */

@Service
class SendVerifyCodeServiceImpl : ISendVerifyCodeService {


    override fun verifyCode(phoneNumber: String, verifyCode: String): Boolean {
        return false
    }

    override fun sendVerifyCode(phoneNumber: String) {

    }

    private fun checkUserCanSendVerifyCode(): Boolean {
        return false
    }

    private fun sendMessage(code: VerifyCode) {

    }
}
