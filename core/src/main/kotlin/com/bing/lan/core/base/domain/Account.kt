package com.bing.lan.core.base.domain

import com.bing.lan.core.base.utils.BidConst
import lombok.Getter
import lombok.Setter
import org.apache.ibatis.type.Alias
import java.math.BigDecimal

/**
 * Created by 蓝兵 on 2018/6/1.
 */

@Getter
@Setter
@Alias("Account")
class Account : BaseDomain() {
    var version: Int = 0
    var tradePassword: String? = null  // 交易密码
    var usableAmount = BidConst.ZERO // 可用余额
    var freezedAmount = BidConst.ZERO  // 冻结金额
    var unReceiveInterest = BidConst.ZERO // 账户待收利息
    var unReceivePrincipal = BidConst.ZERO  // 账户待收本金
    var unReturnAmount = BidConst.ZERO  // 账户待还金额
    var remainBorrowLimit = BidConst.ZERO  // 账户剩余授信额度
    var borrowLimitAmount: BigDecimal? = null  //  授信额度（当前还可以信用借款额度）

    val totalAmount: BigDecimal
        get() = this.usableAmount.add(this.freezedAmount).add(this.unReceivePrincipal)

    companion object {

        private val serialVersionUID = 6760287512112252557L

        fun empty(id: Long): Account {
            val account = Account()
            account.id = id
            account.borrowLimitAmount = BidConst.DEFALUT_BORROWLIMITAMOUNT
            account.remainBorrowLimit = BidConst.DEFALUT_BORROWLIMITAMOUNT
            return account
        }
    }
}