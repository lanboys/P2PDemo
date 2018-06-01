package com.bing.lan.core.base.mapper

import com.bing.lan.core.base.domain.Account

interface AccountMapper {
    fun deleteByPrimaryKey(id: Long?): Int

    fun insert(record: Account): Int

    fun selectByPrimaryKey(id: Long?): Account

    fun selectAll(): List<Account>

    fun updateByPrimaryKey(record: Account): Int
}