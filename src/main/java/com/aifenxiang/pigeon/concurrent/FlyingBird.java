package com.aifenxiang.pigeon.concurrent;

import com.aifenxiang.pigeon.server.EmailApplication;
import com.aifenxiang.pigeon.service.EmailSendService;
import lombok.Data;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang.exception.ExceptionUtils;

import static com.aifenxiang.pigeon.concurrent.MailCourierClient.queue;

/**
 * @author: zj
 * @create: 2018-08-24 14:52
 **/
@Data
@Log4j2
public class FlyingBird implements Runnable {

    @Override
    public void run() {
        try {
            EmailApplication poll = queue.poll();
            if (poll == null){
                return;
            }
            EmailSendService emailSendService = poll.getEmailSendService();
            emailSendService.sendMessage(poll);
        }catch (Exception e){
            log.error(ExceptionUtils.getFullStackTrace(e));
        }

    }
}
