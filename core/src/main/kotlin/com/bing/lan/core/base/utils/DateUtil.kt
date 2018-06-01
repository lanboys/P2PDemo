package com.bing.lan.core.base.utils

import org.apache.commons.lang.time.DateUtils
import java.util.*

object DateUtil {

    fun endOfDay(d: Date): Date {
        return DateUtils.addSeconds(
                DateUtils.addDays(DateUtils.truncate(d, Calendar.DATE), 1), -1)
    }

    fun getSecondsBetweenDates(d1: Date, d2: Date): Long {
        return Math.abs((d1.time - d2.time) / 1000)
    }
}
