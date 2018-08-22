package com.aifenxiang.pigeon.autoconfigartion;

import com.aifenxiang.pigeon.autoconfigartion.AiFenXIangMailProperties;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author: zj
 * @create: 2018-08-22 23:36
 **/
@NoArgsConstructor
@Data
public class AiFenXiangMailService {

    private AiFenXIangMailProperties aiFenXIangMailProperties;

    public AiFenXiangMailService(AiFenXIangMailProperties aiFenXIangMailProperties){
        aiFenXIangMailProperties.verifyParams();
    }

}
