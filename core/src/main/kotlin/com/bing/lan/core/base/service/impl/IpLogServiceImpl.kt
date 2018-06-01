package com.bing.lan.core.base.service.impl

import com.bing.lan.core.base.domain.IpLog
import com.bing.lan.core.base.mapper.IpLogMapper
import com.bing.lan.core.base.query.IpLogQueryObject
import com.bing.lan.core.base.query.PageResult
import com.bing.lan.core.base.service.IIpLogService

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class IpLogServiceImpl : IIpLogService {

    @Autowired
    private val ipLogMapper: IpLogMapper? = null

    override fun query(qo: IpLogQueryObject): PageResult {
        val count = this.ipLogMapper!!.queryForCount(qo)
        if (count > 0) {
            val list = this.ipLogMapper.query(qo)
            return PageResult(count, qo.pageSize, qo.currentPage, list)
        }

        return PageResult.empty(qo.pageSize)
    }

    override fun insert(ipLog: IpLog) {

    }
}
