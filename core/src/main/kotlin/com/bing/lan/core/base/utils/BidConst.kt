package com.bing.lan.core.base.utils

import java.math.BigDecimal

/**
 * 系统需要的常量
 * Created by 蓝兵 on 2018/5/8.
 */
object BidConst {

    val DISPLAY_SCALE = 2//显示精度
    val CAL_SCALE = 8//计算精度
    val STORE_SCALE = 4//保存精度

    val ZERO = BigDecimal("0.0000")//系统中需要的zero
    val DEFALUT_BORROWLIMITAMOUNT = BigDecimal(
            "2000.0000")//初始用户授信额度

    val DEFAULT_ADMIN_NAME = "admin"
    val DEFAULT_ADMIN_PASSWORD = "1111"

    val CREDIT_BORROW_SCORE = 30//信用信用分数
}