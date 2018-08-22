package com.aifenxiang.pigeon.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author: zj
 * @create: 2018-08-23 01:31
 **/
@Data
@AllArgsConstructor
public class AiMailException extends RuntimeException {

    private String message;



}
