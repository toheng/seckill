package com.hengo.exception;

/**
 * 重复秒杀异常
 * Created by Hengo.
 * 2018/3/28 22:01
 */
public class RepeatKillException extends SeckillException {

    public RepeatKillException(String message) {
        super(message);
    }

    public RepeatKillException(String message, Throwable cause) {
        super(message, cause);
    }

}
