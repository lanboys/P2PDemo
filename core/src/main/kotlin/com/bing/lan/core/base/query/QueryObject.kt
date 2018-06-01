package com.bing.lan.core.base.query

import lombok.Getter
import lombok.Setter

/**
 * Created by 蓝兵 on 2018/6/1.
 */
@Getter
@Setter
open class QueryObject {

    var currentPage = 1
        set(currentPage) {
            field = this.currentPage
        }
    var pageSize = 10
        set(pageSize) {
            field = this.pageSize
        }

    val start: Int
        get() = (this.currentPage - 1) * this.pageSize

}
