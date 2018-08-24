package com.aifenxiang.pigeon.concurrent;

import com.aifenxiang.pigeon.exception.AiMailException;
import com.aifenxiang.pigeon.factory.ThreadFactory;
import com.aifenxiang.pigeon.server.EmailApplication;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * @author: zj
 * @create: 2018-08-24 14:50
 **/
@Component
@Data
public class SendMailUtil {


    private volatile static BlockingQueue<EmailApplication> queue = new ArrayBlockingQueue<EmailApplication>(1000);


    @Value("${email.send.num}")
    private int threadNum;


    public static long TIME_OUT = 3*60*1000;


    public boolean send(EmailApplication emailApplication){
        if ( null == emailApplication){
            throw new AiMailException("Please check the parameters");
        }
        boolean rsp = false;
        try {
            rsp = queue.offer(emailApplication, 3, TimeUnit.SECONDS);
            if (!rsp){
                throw new AiMailException("The server is busy, please resend it later");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return rsp;
    }

    @Scheduled(fixedDelay = 2000)
    public void executeSend(){
        for (int i=1;i<=threadNum;i++){
            ThreadFactory.init(threadNum).execute(new FlyingBird(queue));
        }
    }

}
