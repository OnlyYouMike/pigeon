package com.aifenxiang.pigeon.service.impl;

import com.aifenxiang.pigeon.autoconfigartion.AiFenXiangMailService;
import com.aifenxiang.pigeon.exception.AiMailException;
import com.aifenxiang.pigeon.server.EmailApplication;
import com.aifenxiang.pigeon.service.EmailSendService;
import com.aifenxiang.pigeon.service.TemplateService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.util.CollectionUtils;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

/**
 * @author: zj
 * @create: 2018-08-23 17:53
 **/
@Log4j2
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SendTemplateMailServiceImpl  implements EmailSendService {

    @Autowired
    private JavaMailSender javaMailSender;
    @Autowired
    private AiFenXiangMailService aiFenXiangMailService;

    @Override
    public void sendMessage(EmailApplication emailApplication) {
        if (null == emailApplication){
            throw new AiMailException("Mail recipient configuration cannot be empty");
        }
        emailApplication.verify();
        verifyTemplateValue(emailApplication);
        sendTemplateMessage(emailApplication);
    }

    private void verifyTemplateValue(EmailApplication emailApplication){
        if (CollectionUtils.isEmpty(emailApplication.getContextTemplate())){
            throw new AiMailException("Template message cannot be empty using template mail");
        }
    }

    private void sendTemplateMessage(EmailApplication emailApplication) {
        try {
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
            mimeMessageHelper.setFrom(aiFenXiangMailService.getSenderMail());
            mimeMessageHelper.setTo(emailApplication.getRecipientMail());
            mimeMessageHelper.setSubject(emailApplication.getTitle());
            String context = emailApplication.getTemplateService().render(aiFenXiangMailService.getLocalTemplatePath(), emailApplication.getContextTemplate());
            mimeMessageHelper.setText(context);
            javaMailSender.send(mimeMessage);
            log.info("The template email is sent successfully, the recipient email:{"+emailApplication.getRecipientMail()+"},title:{"+emailApplication.getTitle()+"}");
        } catch (MessagingException e) {
            throw new RuntimeException(ExceptionUtils.getFullStackTrace(e));
        }

    }

}
