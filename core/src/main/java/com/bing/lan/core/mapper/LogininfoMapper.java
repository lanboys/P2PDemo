package com.bing.lan.core.mapper;

import com.bing.lan.core.domain.Logininfo;

import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface LogininfoMapper {
    
    int deleteByPrimaryKey(Long id);

    int insert(Logininfo record);

    Logininfo selectByPrimaryKey(Long id);

    List<Logininfo> selectAll();

    int updateByPrimaryKey(Logininfo record);

    int getCountByUsername(@Param("username") String username);
}