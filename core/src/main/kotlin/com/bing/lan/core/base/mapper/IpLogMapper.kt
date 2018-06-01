package com.bing.lan.core.base.mapper

import com.bing.lan.core.base.domain.IpLog
import com.bing.lan.core.base.query.IpLogQueryObject

interface IpLogMapper {

    fun deleteByPrimaryKey(id: Long?): Int

    fun insert(record: IpLog): Int

    fun selectByPrimaryKey(id: Long?): IpLog

    fun selectAll(): List<IpLog>

    fun updateByPrimaryKey(record: IpLog): Int

    fun queryForCount(qo: IpLogQueryObject): Int

    fun query(qo: IpLogQueryObject): List<IpLog>
}