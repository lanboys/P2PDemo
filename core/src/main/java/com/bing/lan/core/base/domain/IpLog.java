package com.bing.lan.core.base.domain;

import org.apache.ibatis.type.Alias;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by 蓝兵 on 2018/5/9.
 * <p>
 * 登陆日志
 */
@Getter
@Setter
@Alias("IpLog")
public class IpLog extends BaseDomain {

    public static int LOGINSTATE_FAILD = 0;//登陆失败
    public static int LOGINSTATE_SUCCESS = 1;//登陆成功

    private String username;
    private Date loginTime;
    private String ip;

    public int loginState = LOGINSTATE_FAILD;
    public int loginType;
    public Long loginInfoId;

    public String getDisplayState() {
        return this.loginState == LOGINSTATE_FAILD ? "登录失败" : "登录成功";
    }

    public IpLog() {
        super();
    }

    public IpLog(String username, Date loginTime, String ip, int loginType,
            Long loginInfoId) {
        super();
        this.username = username;
        this.loginTime = loginTime;
        this.ip = ip;
        this.loginState = IpLog.LOGINSTATE_FAILD;
        this.loginType = loginType;
        this.loginInfoId = loginInfoId;
    }

    public Long getLoginInfoId() {
        return loginInfoId;
    }

    public void setLoginInfoId(Long loginInfoId) {
        this.loginInfoId = loginInfoId;
    }
}
