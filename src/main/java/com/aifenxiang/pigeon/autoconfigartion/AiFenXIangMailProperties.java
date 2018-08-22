package com.aifenxiang.pigeon.autoconfigartion;

import com.aifenxiang.pigeon.exception.AiMailException;
import lombok.Data;
import org.apache.commons.lang.StringUtils;
import org.springframework.boot.context.properties.ConfigurationProperties;

import javax.net.ssl.SSLSocketFactory;

/**
 * @author: zj
 * @create: 2018-08-22 22:37
 **/
@Data
@ConfigurationProperties(prefix = "aifenxiang.mail")
public class AiFenXIangMailProperties {

    private String senderMail;

    private String localTemplatePath;

    private String username;

    private String password;

    private String protocol;

    private String port;

    private String host;

    private String defaultEncoding = "UTF-8";

    private boolean smtpAuth = true;

    private boolean smtpSslEnable = false;

    private Class clazz = SSLSocketFactory.class;

    private boolean fallback = true;


    public void verifyParams(){
        if (StringUtils.isBlank(this.senderMail)){
            throw new AiMailException("PleaseConfigureTheSenderEmailAccount");
        }

    }


}
