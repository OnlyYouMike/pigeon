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
import static com.aifenxiang.pigeon.concurrent.SendMailUtil.isRuning;

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
    public  void run() {
        long timestamp = System.currentTimeMillis();
        while (isRuning){
            long timeOut  = timestamp+TIME_OUT;
            if (System.currentTimeMillis()>timeOut){
                break;
            }
            try {
                if (queue.size()==0){
                    Thread.sleep(2000);
                    if (queue.size() == 0){
                        isRuning = false;
                        log.warn("End of the mailing process");
                        break;
                    }
                    continue;
                }
                timestamp = System.currentTimeMillis();
                EmailApplication emailApplication = queue.poll();
                EmailSendService emailSendService = emailApplication.getEmailSendService();
                emailSendService.sendMessage(emailApplication);
                Thread.sleep(1000);
            }catch (Exception e){
                log.error("Mail sending error:"+ExceptionUtils.getFullStackTrace(e));
            }

        }
    }
}
