package com.bing.lan.core.base.domain

import lombok.Getter
import lombok.Setter
import org.apache.ibatis.type.Alias
import java.util.*

/**
 * Created by 蓝兵 on 2018/5/11.
 */


@Alias("EmailActive")
@Getter
@Setter
class EmailActive : BaseDomain() {

    public lateinit var uuidcode: String
    public var logininfoId: Long = 0
    public lateinit var email: String
    public lateinit var sendDate: Date
}
