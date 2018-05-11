package com.bing.lan.core.business.service.impl;

import com.bing.lan.core.base.ServiceRuntimeException;
import com.bing.lan.core.base.domain.EmailActive;
import com.bing.lan.core.base.domain.Userinfo;
import com.bing.lan.core.base.mapper.EmailActiveMapper;
import com.bing.lan.core.base.utils.BitStatesUtils;
import com.bing.lan.core.base.utils.DateUtil;
import com.bing.lan.core.base.utils.UserContext;
import com.bing.lan.core.business.service.IEmailActiveService;
import com.bing.lan.core.business.service.IUserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Properties;
import java.util.UUID;

import javax.mail.internet.MimeMessage;

@Service
public class EmailActiveServiceImpl implements IEmailActiveService {

    @Value("${mail.host}")
    private String host;

    @Value("${mail.from}")
    private String from;

    @Value("${mail.siteurl}")
    private String siteurl;

    @Value("${mail.username}")
    private String username;

    @Value("${mail.password}")
    private String password;

    @Autowired
    private EmailActiveMapper emailActiveMapper;

    @Autowired
    private IUserService userService;

    @Override
    public void sendActiveEmail(String email) {
        EmailActive ea = new EmailActive();
        ea.setEmail(email);
        ea.setLogininfoId(UserContext.INSTANCE.getLogininfo().id);
        ea.setSendDate(new Date());
        ea.setUuidcode(UUID.randomUUID().toString());

        //发送邮件
        try {
            sendEmail(ea);
            emailActiveMapper.insert(ea);
        } catch (Exception e) {
            throw new ServiceRuntimeException(e.getLocalizedMessage());
        }
    }

    private void sendEmail(EmailActive mail) throws Exception {
        StringBuilder content = new StringBuilder(100)
                .append("<html><head></head><body><h1>这是你的邮箱激活邮件,请点击<a href='")
                .append(siteurl).append("checkMailActive.do?code=")
                .append(mail.getUuidcode())
                .append("'>这里</a>,激活邮箱.有效期为3天!<h1></body></html>");

        Properties p = new Properties();
        p.setProperty("mail.smtp.auth", "true");
        p.setProperty("mail.smtp.timeout", "25000");

        JavaMailSenderImpl sender = new JavaMailSenderImpl();

        MimeMessage message = sender.createMimeMessage();
        MimeMessageHelper messageHelper = new MimeMessageHelper(message, "UTF-8");
        messageHelper.setTo(mail.getEmail());
        messageHelper.setFrom("Admin@android.com");
        messageHelper.setFrom(from);
        messageHelper.setSubject("激活你的邮箱");
        messageHelper.setText(content.toString(), true);

        //sender.setHost("127.0.0.1");
        //sender.setUsername("Admin");
        //sender.setPassword("1111");

        sender.setHost(host);
        sender.setUsername(username);
        sender.setPassword(password);
        sender.setJavaMailProperties(p);
        sender.send(message);
    }

    @Override
    public void bindEmail(String code) {
        EmailActive email = this.emailActiveMapper.selectByCode(code);
        if (email != null
                && DateUtil.getSecondsBetweenDates(email.getSendDate(),
                new Date()) <= 60 * 60 * 24 * 3) {
            Userinfo user = this.userService.get(email.getLogininfoId());
            if (!user.getIsBindEmail()) {
                user.setEmail(email.getEmail());
                user.addState(BitStatesUtils.OP_BIND_EMAIL);
                this.userService.update(user);
                return;
            } else {
                throw new RuntimeException("邮件已经激活!");
            }
        }
        throw new ServiceRuntimeException("邮件激活时间超时,请登录系统重新激活!");
    }
}
