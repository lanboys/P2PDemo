package com.bing.lan.core.base.mapper

import com.bing.lan.core.base.domain.SystemDictionaryItem
import com.bing.lan.core.base.domain.SystemDictionaryQueryObject

import org.apache.ibatis.annotations.Param

interface SystemDictionaryItemMapper {

    fun deleteByPrimaryKey(id: Long?): Int

    fun insert(record: SystemDictionaryItem): Int

    fun selectByPrimaryKey(id: Long?): SystemDictionaryItem

    fun selectAll(): List<SystemDictionaryItem>

    fun updateByPrimaryKey(record: SystemDictionaryItem): Int

    fun queryForCount(qo: SystemDictionaryQueryObject): Int

    fun query(qo: SystemDictionaryQueryObject): List<SystemDictionaryItem>

    /**
     * 按照数据字典的目录sn查所有明细
     *
     * @param sn
     * @return
     */
    fun queryBySn(@Param("sn") sn: String): List<SystemDictionaryItem>
}