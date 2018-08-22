package com.aifenxiang.pigeon.autoconfigartion;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author: zj
 * @create: 2018-08-22 23:40
 **/
@Configuration
@EnableConfigurationProperties(AiFenXIangMailProperties.class)
@ConditionalOnClass(AiFenXiangMailService.class)
@ConditionalOnProperty(prefix = "aifenxiang.mail",value = "enabled",matchIfMissing = true)
public class AIFenXiangServiceAutoConfigartion {

    @Autowired
    private AiFenXIangMailProperties aiFenXIangMailProperties;

    @Bean
    @ConditionalOnMissingBean(AiFenXiangMailService.class)
    public AiFenXiangMailService aiFenXiangMailService(){
        AiFenXiangMailService aiFenXiangMailService = new AiFenXiangMailService(aiFenXIangMailProperties);
        return aiFenXiangMailService;
    }

}
