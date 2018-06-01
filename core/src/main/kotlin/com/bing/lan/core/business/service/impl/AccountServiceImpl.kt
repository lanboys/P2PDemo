package com.bing.lan.core.business.service.impl

import com.bing.lan.core.base.domain.Account
import com.bing.lan.core.base.mapper.AccountMapper
import com.bing.lan.core.business.service.IAccountService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

/**
 * Created by 蓝兵 on 2018/5/7.
 */
@Service
open class AccountServiceImpl : IAccountService {

    @Autowired
    lateinit var accountMapper: AccountMapper

    override fun update(account: Account) {
        val ret = accountMapper.updateByPrimaryKey(account)
        if (ret <= 0) {
            throw RuntimeException("Account对象:" + account.id
                    + " 乐观锁失败!")
        }
    }

    override fun getAccount(id: Long): Account {
        return accountMapper.selectByPrimaryKey(id)
    }
}