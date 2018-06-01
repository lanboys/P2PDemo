package com.bing.lan.core.base.mapper

import com.bing.lan.core.base.domain.EmailActive

interface EmailActiveMapper {
    fun deleteByPrimaryKey(id: Long?): Int

    fun insert(record: EmailActive): Int

    fun selectByPrimaryKey(id: Long?): EmailActive

    fun selectAll(): List<EmailActive>

    fun updateByPrimaryKey(record: EmailActive): Int

    fun selectByCode(code: String): EmailActive
}