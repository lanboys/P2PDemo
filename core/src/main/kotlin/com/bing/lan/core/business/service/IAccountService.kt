package com.bing.lan.core.business.service

import com.bing.lan.core.base.domain.Account

/**
 * Created by 蓝兵 on 2018/5/7.
 */

interface IAccountService {

    fun update(account: Account)

    fun getAccount(id: Long): Account
}
