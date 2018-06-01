package com.bing.lan.core.base.mapper

import com.bing.lan.core.base.domain.Userinfo

interface UserinfoMapper {

    fun deleteByPrimaryKey(id: Long?): Int

    fun insert(record: Userinfo): Int

    fun selectByPrimaryKey(id: Long?): Userinfo

    fun selectAll(): List<Userinfo>

    fun updateByPrimaryKey(record: Userinfo): Int
}