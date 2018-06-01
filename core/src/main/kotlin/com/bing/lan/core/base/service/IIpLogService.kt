package com.bing.lan.core.base.service

import com.bing.lan.core.base.domain.IpLog
import com.bing.lan.core.base.query.IpLogQueryObject
import com.bing.lan.core.base.query.PageResult

/**
 * Created by 蓝兵 on 2018/6/1.
 */
interface IIpLogService {

    fun query(qo: IpLogQueryObject): PageResult

    fun insert(ipLog: IpLog)
}
