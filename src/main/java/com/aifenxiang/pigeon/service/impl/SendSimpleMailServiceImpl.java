package com.aifenxiang.pigeon.service.impl;

import com.aifenxiang.pigeon.autoconfigartion.AiFenXiangMailService;
import com.aifenxiang.pigeon.config.dto.Pair;
import com.aifenxiang.pigeon.exception.AiMailException;
import com.aifenxiang.pigeon.server.EmailApplication;
import com.aifenxiang.pigeon.service.EmailSendService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.internet.MimeMessage;
import java.io.File;

/**
 * @author: zj
 * @create: 2018-08-23 17:31
 **/
@Slf4j
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SendSimpleMailServiceImpl  implements EmailSendService {


    @Autowired
    private AiFenXiangMailService aiFenXiangMailService;

    @Autowired
    private JavaMailSender javaMailSender;


    @Override
    public void sendMessage(EmailApplication emailApplication) {
        if (null == emailApplication){
            throw new AiMailException("Mail recipient configuration cannot be empty");
        }
        emailApplication.verify();
        sendSimpleMessage(emailApplication);
    }

    private void sendSimpleMessage(EmailApplication emailApplication) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(aiFenXiangMailService.getSenderMail());
        message.setTo(emailApplication.getRecipientMail());
        message.setSubject(emailApplication.getTitle());
        message.setText(emailApplication.getContext());
        javaMailSender.send(message);
        log.info("The  email is sent successfully, the recipient email:{"+emailApplication.getRecipientMail()+"},title:{"+emailApplication.getTitle()+"}");
    }

}
