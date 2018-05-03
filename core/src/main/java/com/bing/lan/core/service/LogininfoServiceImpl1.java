//package com.bing.lan.core.service;
//
//import com.bing.lan.core.ServiceRuntimeException;
//import com.bing.lan.core.domain.Logininfo;
//import com.bing.lan.core.mapper.LogininfoMapper;
//
//import org.jetbrains.annotations.NotNull;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
///**
// * Created by 蓝兵 on 2018/5/3.
// */
//
//@Service
//class LogininfoServiceImpl1 implements ILogininfoService {
//
//    @Autowired(required = false)
//    LogininfoMapper logininfoMapper;
//
//    public void register(@NotNull String username, @NotNull String password) {
//        int count = logininfoMapper.getCountByUsername(username);
//        if (count <= 0) {
//            Logininfo logininfo = new Logininfo();
//            logininfo.setPassword(password);
//            logininfo.setUsername(username);
//            logininfoMapper.insert(logininfo);
//        } else {
//            throw new ServiceRuntimeException("用户已经存在!!");
//        }
//    }
//}
