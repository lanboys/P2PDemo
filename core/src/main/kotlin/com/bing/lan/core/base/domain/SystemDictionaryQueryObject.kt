package com.bing.lan.core.base.domain

import com.bing.lan.core.base.query.QueryObject
import lombok.Getter
import lombok.Setter
import org.apache.ibatis.type.Alias
import org.springframework.util.StringUtils

@Getter
@Setter
@Alias("SystemDictionaryQueryObject")
class SystemDictionaryQueryObject : QueryObject() {
    private val keyword: String? = null
    val parentId: Long? = null

    fun getKeyword(): String? {
        return if (StringUtils.hasLength(keyword)) keyword else null
    }
}
