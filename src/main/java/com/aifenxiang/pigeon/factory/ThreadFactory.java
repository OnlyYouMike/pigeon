package com.aifenxiang.pigeon.factory;

import com.aifenxiang.pigeon.exception.AiMailException;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author: zj
 * @create: 2018-08-24 14:44
 **/
public class ThreadFactory {

    private  static ExecutorService executorService;

    public  static  ExecutorService init(int threadNum){
        if (threadNum <= 0){
            throw new AiMailException("The number of thread pool initializations cannot be empty");
        }
        if (executorService == null){
            executorService = Executors.newFixedThreadPool(threadNum);
        }
        return executorService;
    }


}
