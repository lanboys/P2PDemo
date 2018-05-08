package com.bing.lan.core.base.domain;

import com.bing.lan.core.base.utils.BidConst;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Account extends BaseDomain {

    private static final long serialVersionUID = 6760287512112252557L;
    private int version;
    private String tradePassword; // 交易密码
    private BigDecimal usableAmount = BidConst.ZERO; // 可用余额
    private BigDecimal freezedAmount = BidConst.ZERO; // 冻结金额
    private BigDecimal unReceiveInterest = BidConst.ZERO; // 账户待收利息
    private BigDecimal unReceivePrincipal = BidConst.ZERO; // 账户待收本金
    private BigDecimal unReturnAmount = BidConst.ZERO; // 账户待还金额
    private BigDecimal remainBorrowLimit = BidConst.ZERO; // 账户剩余授信额度
    private BigDecimal borrowLimitAmount; // 授信额度（当前还可以信用借款额度）

    public BigDecimal getTotalAmount() {
        return usableAmount.add(freezedAmount).add(unReceivePrincipal);
    }

    public static Account empty(Long id) {
        Account account = new Account();
        account.setId(id);
        account.setBorrowLimitAmount(BidConst.DEFALUT_BORROWLIMITAMOUNT);
        account.setRemainBorrowLimit(BidConst.DEFALUT_BORROWLIMITAMOUNT);
        return account;
    }
}