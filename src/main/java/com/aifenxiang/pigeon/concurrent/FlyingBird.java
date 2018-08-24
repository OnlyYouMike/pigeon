package com.aifenxiang.pigeon.concurrent;

import com.aifenxiang.pigeon.server.EmailApplication;
import com.aifenxiang.pigeon.service.EmailSendService;
import com.aifenxiang.pigeon.service.TemplateService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang.exception.ExceptionUtils;

import java.util.concurrent.BlockingQueue;

import static com.aifenxiang.pigeon.concurrent.SendMailUtil.TIME_OUT;

/**
 * @author: zj
 * @create: 2018-08-24 14:52
 **/
@Data
@AllArgsConstructor
@Log4j2
public class FlyingBird implements Runnable {

    private BlockingQueue<EmailApplication> queue;
    @Override
    public void run() {
        EmailApplication poll = queue.poll();
        if (poll != null){
            EmailSendService emailSendService = poll.getEmailSendService();
            emailSendService.sendMessage(poll);
        }
    }
}
