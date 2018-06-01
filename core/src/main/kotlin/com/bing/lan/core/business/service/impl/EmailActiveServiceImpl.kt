package com.bing.lan.core.business.service.impl

import com.bing.lan.core.base.ServiceRuntimeException
import com.bing.lan.core.base.domain.EmailActive
import com.bing.lan.core.base.mapper.EmailActiveMapper
import com.bing.lan.core.base.utils.BitStatesUtils
import com.bing.lan.core.base.utils.DateUtil
import com.bing.lan.core.base.utils.UserContext
import com.bing.lan.core.business.service.IEmailActiveService
import com.bing.lan.core.business.service.IUserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.mail.javamail.JavaMailSenderImpl
import org.springframework.mail.javamail.MimeMessageHelper
import org.springframework.stereotype.Service
import java.util.*

@Service
class EmailActiveServiceImpl : IEmailActiveService {

    //@Value("${mail.host}")
    @Value("#{propertiesBean['mail.host']}")
    private val host: String? = null

    //@Value("${mail.from}")
    @Value("#{propertiesBean['mail.from']}")
    private val from: String? = null

    //@Value("${mail.siteurl}")
    @Value("#{propertiesBean['mail.siteurl']}")
    private val siteurl: String? = null

    //@Value("${mail.username}")
    @Value("#{propertiesBean['mail.username']}")
    private val username: String? = null

    //@Value("${mail.password}")
    @Value("#{propertiesBean['mail.password']}")
    private val password: String? = null

    @Autowired
    private val emailActiveMapper: EmailActiveMapper? = null

    @Autowired
    private val userService: IUserService? = null

    override fun sendActiveEmail(email: String) {
        val ea = EmailActive()

        ea.email = email
        ea.logininfoId = UserContext.getLogininfo()!!.id
        ea.sendDate = Date()
        ea.uuidcode = UUID.randomUUID().toString()

        //发送邮件
        try {
            sendEmail(ea)
            emailActiveMapper!!.insert(ea)
        } catch (e: Exception) {
            throw ServiceRuntimeException(e.localizedMessage)
        }

    }

    @Throws(Exception::class)
    private fun sendEmail(mail: EmailActive) {
        val content = StringBuilder(100)
                .append("<html><head></head><body><h1>这是你的邮箱激活邮件,请点击<a href='")
                //.append("http://localhost:8085/").append("checkMailActive.do?code=")
                .append(siteurl).append("checkMailActive.do?code=")
                .append(mail.uuidcode)
                .append("'>这里</a>,激活邮箱.有效期为3天!<h1></body></html>")

        val p = Properties()
        p.setProperty("mail.smtp.auth", "true")
        p.setProperty("mail.smtp.timeout", "25000")

        val sender = JavaMailSenderImpl()

        val message = sender.createMimeMessage()
        val messageHelper = MimeMessageHelper(message, "UTF-8")
        messageHelper.setTo(mail.email)
        //messageHelper.setFrom("Admin@android.com");
        messageHelper.setFrom(from!!)
        messageHelper.setSubject("激活你的邮箱")
        messageHelper.setText(content.toString(), true)

        //sender.setHost("127.0.0.1");
        //sender.setUsername("Admin");
        //sender.setPassword("1111");

        sender.host = host
        sender.username = username
        sender.password = password


        sender.javaMailProperties = p
        sender.send(message)
    }

    override fun bindEmail(code: String) {
        val email = this.emailActiveMapper!!.selectByCode(code)
        if (email != null && DateUtil.getSecondsBetweenDates(email.sendDate,
                        Date()) <= 60 * 60 * 24 * 3) {
            val user = this.userService!!.getUserinfo(email.logininfoId)
            if (!user.isBindEmail) {
                user.email = email.email
                user.addState(BitStatesUtils.OP_BIND_EMAIL)
                this.userService.update(user)
                return
            } else {
                throw RuntimeException("邮件已经激活!")
            }
        }
        throw ServiceRuntimeException("邮件激活时间超时,请登录系统重新激活!")
    }
}
