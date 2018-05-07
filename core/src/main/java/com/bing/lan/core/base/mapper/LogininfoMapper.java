package com.bing.lan.core.base.mapper;

import com.bing.lan.core.base.domain.Logininfo;

import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface LogininfoMapper {

    int deleteByPrimaryKey(Long id);

    int insert(Logininfo record);

    Logininfo selectByPrimaryKey(Long id);

    List<Logininfo> selectAll();

    int updateByPrimaryKey(Logininfo record);

    int getCountByUsername(@Param("username") String username);

    Logininfo login(@Param("username") String username, @Param("password") String password);
}