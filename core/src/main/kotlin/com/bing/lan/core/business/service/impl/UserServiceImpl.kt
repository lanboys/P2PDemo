package com.bing.lan.core.business.service.impl

import com.bing.lan.core.base.domain.Userinfo
import com.bing.lan.core.base.mapper.UserinfoMapper
import com.bing.lan.core.base.utils.BitStatesUtils
import com.bing.lan.core.base.utils.UserContext
import com.bing.lan.core.business.service.ISendVerifyCodeService
import com.bing.lan.core.business.service.IUserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service


/**
 * Created by 蓝兵 on 2018/5/7.
 */

@Service
class UserServiceImpl : IUserService {

    @Autowired
    lateinit var userinfoMapper: UserinfoMapper

    @Autowired
    lateinit var sendVerifyCodeService: ISendVerifyCodeService


    override fun update(userinfo: Userinfo) {
        val ret = userinfoMapper.updateByPrimaryKey(userinfo)
        if (ret <= 0) {
            throw RuntimeException("Userinfo对象:" + userinfo.id
                    + " 乐观锁失败!")
        }
    }

    override fun get(id: Long): Userinfo {
        return userinfoMapper.selectByPrimaryKey(id)
    }

    override fun bindPhone(phoneNumber: String, verifyCode: String) {
        sendVerifyCodeService.verifyCode(phoneNumber, verifyCode)

        val ui = get(UserContext.getLogininfo()!!.id)
        ui.phoneNumber = phoneNumber
        ui.addState(BitStatesUtils.OP_BIND_PHONE)
        update(ui)
    }

    override fun updateBasicInfo(userinfo: Userinfo) {

    }
}
