package com.bing.lan.core.base.service.impl;

import com.bing.lan.core.base.domain.IpLog;
import com.bing.lan.core.base.mapper.IpLogMapper;
import com.bing.lan.core.base.query.IpLogQueryObject;
import com.bing.lan.core.base.query.PageResult;
import com.bing.lan.core.base.service.IIpLogService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IpLogServiceImpl implements IIpLogService {

    @Autowired
    private IpLogMapper ipLogMapper;

    @Override
    public PageResult query(IpLogQueryObject qo) {
        int count = this.ipLogMapper.queryForCount(qo);
        if (count > 0) {
            List<IpLog> list = this.ipLogMapper.query(qo);
            return new PageResult(count, qo.getPageSize(), qo.getCurrentPage(), list);
        }
        return PageResult.empty(qo.getPageSize());
    }

    @Override
    public void insert(IpLog ipLog) {

    }
}
