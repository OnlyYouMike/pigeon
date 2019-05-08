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
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;

/**
 * @author: zj
 * @create: 2018-08-23 17:49
 **/
@Slf4j
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SendAttachmentMailServiceImpl implements EmailSendService {

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
        verifyFile(emailApplication);
        sendAttachmentMessage(emailApplication);
    }
    private void verifyFile(EmailApplication emailApplication){
        if (CollectionUtils.isEmpty(emailApplication.getAttachment())){
            throw new AiMailException("Attachment cannot be empty");
        }
    }
    public void sendAttachmentMessage(EmailApplication emailApplication) {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        try {
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
            mimeMessageHelper.setFrom(aiFenXiangMailService.getSenderMail());
            mimeMessageHelper.setTo(emailApplication.getRecipientMail());
            mimeMessageHelper.setSubject(emailApplication.getTitle());
            mimeMessageHelper.setText(emailApplication.getContext());
            for (Pair<String,File> pair : emailApplication.getAttachment()){
                mimeMessageHelper.addAttachment(pair.getLeft(),new FileSystemResource(pair.getRight()));
            }
            javaMailSender.send(mimeMessage);
            log.info("The attachment email is sent successfully, the recipient email:{"+emailApplication.getRecipientMail()+"},title:{"+emailApplication.getTitle()+"}");
        } catch (MessagingException e) {
            throw new RuntimeException(ExceptionUtils.getFullStackTrace(e));
        }
    }

}
