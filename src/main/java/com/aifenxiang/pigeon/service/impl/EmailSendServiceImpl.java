package com.aifenxiang.pigeon.service.impl;

import com.aifenxiang.pigeon.config.dto.Pair;
import com.aifenxiang.pigeon.server.EmailApplication;
import com.aifenxiang.pigeon.autoconfigartion.AiFenXiangMailService;
import com.aifenxiang.pigeon.service.EmailSendService;

import com.aifenxiang.pigeon.service.TemplateService;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;

/**
 * @author: zj
 * @create: 2018-08-22 21:58
 **/

@Component
public class EmailSendServiceImpl implements EmailSendService {

    @Autowired
    private JavaMailSender javaMailSender;


    @Autowired
    private AiFenXiangMailService aiFenXiangMailService;

    @Autowired
    private TemplateService templateService;



    @Override
    public void sendSimpleMessage(EmailApplication emailApplication) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(aiFenXiangMailService.getAiFenXIangMailProperties().getSenderMail());
        message.setTo(emailApplication.getRecipientMail());
        message.setSubject(emailApplication.getTitle());
        message.setText(emailApplication.getContext());
        javaMailSender.send(message);
    }

    @Override
    public void sendAttachmentMessage(EmailApplication emailApplication) {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        try {
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
            mimeMessageHelper.setFrom(aiFenXiangMailService.getAiFenXIangMailProperties().getSenderMail());
            mimeMessageHelper.setTo(emailApplication.getRecipientMail());
            mimeMessageHelper.setSubject(emailApplication.getTitle());
            mimeMessageHelper.setTo(emailApplication.getContext());
            for (Pair<String,File> pair : emailApplication.getAttachment()){
                mimeMessageHelper.addAttachment(pair.getLeft(),new FileSystemResource(pair.getRight()));
            }
            javaMailSender.send(mimeMessage);
        } catch (MessagingException e) {
            throw new RuntimeException(ExceptionUtils.getFullStackTrace(e));
        }
    }

    @Override
    public void sendTemplateMessage(EmailApplication emailApplication) {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        try {
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
            mimeMessageHelper.setFrom(aiFenXiangMailService.getAiFenXIangMailProperties().getSenderMail());
            mimeMessageHelper.setTo(emailApplication.getRecipientMail());
            mimeMessageHelper.setSubject(emailApplication.getTitle());
            String context = templateService.render(aiFenXiangMailService.getAiFenXIangMailProperties().getLocalTemplatePath(), emailApplication.getContextTemplate());
            javaMailSender.send(mimeMessage);
        } catch (MessagingException e) {
            throw new RuntimeException(ExceptionUtils.getFullStackTrace(e));
        }

    }
}
