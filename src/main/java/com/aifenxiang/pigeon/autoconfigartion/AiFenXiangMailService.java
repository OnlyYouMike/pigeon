package com.aifenxiang.pigeon.autoconfigartion;

import com.aifenxiang.pigeon.autoconfigartion.AiFenXIangMailProperties;
import com.aifenxiang.pigeon.exception.AiMailException;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author: zj
 * @create: 2018-08-22 23:36
 **/
@NoArgsConstructor
@Data
public class AiFenXiangMailService {

    private String senderMail;

    private String localTemplatePath;


    public AiFenXiangMailService(AiFenXIangMailProperties properties){

        if (StringUtils.isBlank(properties.getSenderMail())){
            throw new AiMailException("Please configure the sender's mailbox");
        }
        if (StringUtils.isBlank(properties.getLocalTemplatePath())){
            this.localTemplatePath = "mail/verifyCode";
        }
        this.senderMail = properties.getSenderMail();
    }



}
