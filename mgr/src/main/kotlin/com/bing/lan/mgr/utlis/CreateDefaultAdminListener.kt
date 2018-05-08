package com.bing.lan.mgr.utlis

import com.bing.lan.core.base.service.ILogininfoService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.ApplicationListener
import org.springframework.context.event.ContextRefreshedEvent
import org.springframework.stereotype.Component

/**
 * Created by 蓝兵 on 2018/5/8.
 */


@Component
class CreateDefaultAdminListener : ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    lateinit var logininfoService: ILogininfoService

    override fun onApplicationEvent(event: ContextRefreshedEvent) {
        println("onApplicationEvent(): =================容器监听器==============")
        if (!logininfoService.hasAdmin()) {
            logininfoService.createDefaultAdmin()
        }
    }
}
