package com.aifenxiang.pigeon.service;

import com.aifenxiang.pigeon.server.EmailApplication;

/**
 * @author: zj
 * @create: 2018-08-22 21:57
 **/
public interface EmailSendService {

    public void sendMessage(EmailApplication emailApplication);

}
