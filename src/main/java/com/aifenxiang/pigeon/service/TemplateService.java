package com.aifenxiang.pigeon.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;


import java.util.Map;

/**
 * @author: zj
 * @create: 2018-08-22 23:10
 **/
@Component
public class TemplateService {

    @Autowired
    private SpringTemplateEngine springTemplateEngine;

    public String render(String template, Map<String,Object>params){
        Context context = new Context(LocaleContextHolder.getLocale());
        context.setVariables(params);
        return springTemplateEngine.process(template,context);
    }
}
