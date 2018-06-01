package com.bing.lan.core.base.query

import lombok.Getter
import lombok.Setter
import org.springframework.util.StringUtils

@Getter
@Setter
class SystemDictionaryQueryObject : QueryObject() {
    private val keyword: String? = null
    private val parentId: Long? = null

    fun getKeyword(): String? {
        return if (StringUtils.hasLength(keyword)) keyword else null
    }
}
