package com.hengo.exception;

/**
 * 秒杀相关业务异常
 * Created by Hengo.
 * 2018/3/28 22:11
 */
public class SeckillException extends RuntimeException {

    public SeckillException(String message) {
        super(message);
    }

    public SeckillException(String message, Throwable cause) {
        super(message, cause);
    }
}
