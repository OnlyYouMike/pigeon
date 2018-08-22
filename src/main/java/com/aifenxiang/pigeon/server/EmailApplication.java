package com.aifenxiang.pigeon.server;

import com.aifenxiang.pigeon.config.dto.Pair;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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

}
