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


}
