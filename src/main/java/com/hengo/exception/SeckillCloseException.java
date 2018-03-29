package com.hengo.exception;

/**
 * Created by Hengo.
 * 2018/3/28 22:04
 */
public class SeckillCloseException extends SeckillException {
    public SeckillCloseException(String message) {
        super(message);
    }

    public SeckillCloseException(String message, Throwable cause) {
        super(message, cause);
    }
}
