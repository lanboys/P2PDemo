package com.bing.lan.core.base.domain;

import org.apache.ibatis.type.Alias;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Alias("Logininfo")
public class Logininfo extends BaseDomain {

    public static final int STATE_NORMAL = 0;
    public static final int STATE_LOCK = 1;
    public static final int STATE_DELETE = -1;

    public static final int USERTYPE_NORMAL = 0;//前段用户
    public static final int USERTYPE_SYSTEM = 1;//后台用户

    public String username;
    public String password;
    public int state = STATE_NORMAL;

    public int userType;//用户类型
    public boolean admin = false;
}
