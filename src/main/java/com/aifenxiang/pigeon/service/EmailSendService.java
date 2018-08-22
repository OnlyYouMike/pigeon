package com.aifenxiang.pigeon.service;

import com.aifenxiang.pigeon.server.EmailApplication;

/**
 * @author: zj
 * @create: 2018-08-22 21:57
 **/
public interface EmailSendService {

    /**
     * 发送简单邮件
     * @param emailApplication
     */
    public void sendSimpleMessage(EmailApplication emailApplication);

    /**
     * 发送附件邮件
     * @param emailApplication
     */
    public void sendAttachmentMessage(EmailApplication emailApplication);

    /**
     * 发送模版邮件
     * @param emailApplication
     */
    public void sendTemplateMessage(EmailApplication emailApplication);

}
