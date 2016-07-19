package com.thanks.util.exception;

/**
 * Created by micky on 2016. 7. 18..
 */
public class InternalServerErrorException extends RuntimeException {

    public InternalServerErrorException() {
        super();
    }

    public InternalServerErrorException(String message) {
        super(message);
    }
}
