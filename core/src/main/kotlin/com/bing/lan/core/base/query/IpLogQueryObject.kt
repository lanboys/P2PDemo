package com.bing.lan.core.base.query

import com.bing.lan.core.base.utils.DateUtil
import lombok.Getter
import lombok.Setter
import org.springframework.format.annotation.DateTimeFormat
import java.util.*

/**
 * Created by 蓝兵 on 2018/6/1.
 */
/**
 * IpLog查询对象
 */
@Setter
@Getter
open class IpLogQueryObject : QueryObject() {

    @set:DateTimeFormat(pattern = "yyyy-MM-dd")
    var beginDate: Date? = null
    @set:DateTimeFormat(pattern = "yyyy-MM-dd")
    var endDate: Date? = null
        get() = if (field != null) {
            DateUtil.endOfDay(field)
        } else null
    var username: String? = null
        set(username) {
            field = this.username
        }
    var userType = -1
        set(userType) {
            field = this.userType
        }
    var isLike: Boolean = false
        set(like) {
            field = isLike
        }
    var state = -1
        set(state) {
            field = this.state
        }
}
