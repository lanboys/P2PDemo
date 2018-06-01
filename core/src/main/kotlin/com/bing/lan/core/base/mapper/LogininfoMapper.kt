package com.bing.lan.core.base.mapper

import com.bing.lan.core.base.domain.Logininfo

import org.apache.ibatis.annotations.Param

interface LogininfoMapper {

    fun deleteByPrimaryKey(id: Long?): Int

    fun insert(record: Logininfo): Int

    fun selectByPrimaryKey(id: Long?): Logininfo

    fun selectAll(): List<Logininfo>

    fun updateByPrimaryKey(record: Logininfo): Int

    fun getCountByUsername(@Param("username") username: String, @Param("userType") userType: Int): Int

    fun login(@Param("username") username: String, @Param("password") password: String, @Param("userType") userType: Int): Logininfo
}