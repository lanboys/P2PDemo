package com.bing.lan.core.base.domain;

import org.apache.ibatis.type.Alias;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by 蓝兵 on 2018/5/11.
 */


@Alias("EmailActive")
@Getter
@Setter
public class EmailActive extends BaseDomain {

    private String uuidcode;
    private Long logininfoId;
    private String email;
    private Date sendDate;
}
