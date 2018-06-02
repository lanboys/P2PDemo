package com.bing.lan.core.base.domain;

import com.bing.lan.core.base.utils.BitStatesUtils;

import org.apache.ibatis.type.Alias;
import org.springframework.util.StringUtils;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Alias("Userinfo")
public class Userinfo extends BaseDomain {

    public static final long serialVersionUID = -2194938919842714855L;
    public int version;// 版本号
    public Long bitState = 0l; // 位状态
    public String realName; // 对应实名认证中的真实姓名
    public String idNumber; // 对应实名认证中的身份证号
    public String email; // 用户邮箱
    public String phoneNumber = ""; // 手机号
    public int authScore = 0;//用户当前认证分数
    public Long realauthId;   //用户有效的实名认证对象

    // ====================== 会员基本资料 ===================
    public SystemDictionaryItem incomeGrade; // 月收入
    public SystemDictionaryItem marriage; // 婚姻情况
    public SystemDictionaryItem kidCount; // 子女情况
    public SystemDictionaryItem educationBackground; // 学历
    public SystemDictionaryItem houseCondition; // 住房条件

    public static Userinfo empty(Long id) {
        Userinfo userinfo = new Userinfo();
        userinfo.setId(id);
        userinfo.bitState = BitStatesUtils.OP_BASIC_INFO;
        return userinfo;
    }

    public void addState(Long state) {
        this.bitState = BitStatesUtils.addState(this.bitState, state);
    }

    public boolean getIsBindPhone() {
        return BitStatesUtils.hasState(bitState, BitStatesUtils.OP_BIND_PHONE);
    }

    public boolean getIsBindEmail() {
        return BitStatesUtils.hasState(bitState, BitStatesUtils.OP_BIND_EMAIL);
    }

    public boolean getBaseInfo() {
        return BitStatesUtils.hasState(bitState, BitStatesUtils.OP_BASE_INFO);
    }

    public boolean getRealAuth() {
        return BitStatesUtils.hasState(bitState, BitStatesUtils.OP_REAL_AUTH);
    }

    public boolean getVedioAuth() {
        return BitStatesUtils.hasState(bitState, BitStatesUtils.OP_VEDIO_AUTH);
    }

    /**
     * 获取用户真实名字的隐藏字符串，只显示姓氏
     *
     * @param realName 真实名字
     * @return
     */
    public String getAnonymousRealName() {
        if (StringUtils.hasLength(realName)) {
            int len = realName.length();
            String replace = "";
            replace += realName.charAt(0);
            for (int i = 1; i < len; i++) {
                replace += "*";
            }
            return replace;
        }
        return realName;
    }
}