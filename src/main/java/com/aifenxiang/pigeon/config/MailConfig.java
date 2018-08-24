package com.aifenxiang.pigeon.config;

import com.aifenxiang.pigeon.autoconfigartion.AiFenXIangMailProperties;
import com.aifenxiang.pigeon.autoconfigartion.AiFenXiangMailService;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

/**
 * @author: zj
 * @create: 2018-08-23 01:24
 **/
@Configuration
public class MailConfig {
    @Bean
    @ConfigurationProperties(prefix = "spring.mail")
    public JavaMailSender javaMailSender(){
        JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();
        return javaMailSender;
    }




}
