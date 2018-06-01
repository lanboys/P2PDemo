package com.bing.lan.core.base.domain

import lombok.Getter
import lombok.Setter
import java.io.Serializable

/**
 * Created by 蓝兵 on 2018/6/1.
 */
@Getter
@Setter
open class BaseDomain : Serializable {

    var id: Long = 0
}
