package com.bing.lan.core.base.mapper;

import com.bing.lan.core.base.domain.IpLog;
import com.bing.lan.core.base.query.IpLogQueryObject;

import java.util.List;

public interface IpLogMapper {

    int deleteByPrimaryKey(Long id);

    int insert(IpLog record);

    IpLog selectByPrimaryKey(Long id);

    List<IpLog> selectAll();

    int updateByPrimaryKey(IpLog record);

    int queryForCount(IpLogQueryObject qo);

    List<IpLog> query(IpLogQueryObject qo);
}