package com.bing.lan.core.base.query

import java.util.*

/**
 * Created by 蓝兵 on 2018/6/1.
 */
open class PageResult {

    var totalCount: Int? = null
    var pageSize: Int? = 10
    var currentPage: Int? = 1
    var result: List<Any>? = null

    val totalPage: Int?
        get() = Math.max((totalCount!! + pageSize!! - 1) / pageSize!!, 1)

    val prev: Int?
        get() = Math.max(currentPage!! - 1, 1)

    val next: Int?
        get() = Math.min(currentPage!! + 1, totalPage!!)

    constructor() {

    }

    constructor(totalCount: Int?, pageSize: Int?,
                currentPage: Int?, result: List<Any>) : super() {
        this.totalCount = totalCount
        this.pageSize = pageSize
        this.currentPage = currentPage
        this.result = result
    }

    companion object {

        fun empty(pageSize: Int): PageResult {
            return PageResult(0, pageSize, 1, ArrayList<Any>())
        }
    }

}
