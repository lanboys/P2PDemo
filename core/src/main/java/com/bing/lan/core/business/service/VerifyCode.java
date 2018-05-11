package com.bing.lan.core.business.service;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class VerifyCode {
    public String phoneNumber;
    public String randomCode;
    public Date lastSendTime;
    public String content;

    public VerifyCode() {
        super();
    }

    public VerifyCode(String phoneNumber, String randomCode, Date lastSendTime,
            String content) {
        super();
        this.phoneNumber = phoneNumber;
        this.randomCode = randomCode;
        this.lastSendTime = lastSendTime;
        this.content = content;
    }
}
