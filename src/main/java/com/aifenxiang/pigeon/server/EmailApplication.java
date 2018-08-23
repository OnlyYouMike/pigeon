package com.aifenxiang.pigeon.server;

import com.aifenxiang.pigeon.config.dto.Pair;
import com.aifenxiang.pigeon.exception.AiMailException;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang.StringUtils;

import java.io.File;
import java.util.List;
import java.util.Map;

/**
 * @author: zj
 * @create: 2018-08-22 21:44
 **/

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmailApplication {


    private String recipientMail;

    private String title;

    private String context;

    private List<Pair<String,File>> attachment;

    private Map<String,Object> contextTemplate;


    public void verify(){
        if (StringUtils.isBlank(recipientMail)){
            throw new AiMailException("Sender email account cannot be empty");
        }
        if (StringUtils.isBlank(title)){
            System.out.print("The subject of the message is not set and has been set to no title by default.");
            this.title = "Untitled";
        }
        if(StringUtils.isBlank(context)){
            this.context = "";
        }
    }


}
