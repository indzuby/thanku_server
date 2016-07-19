package com.thanks.util.exception;

/**
 * Created by micky on 2016. 7. 18..
 */
public class UnauthorizedException extends RuntimeException {
    public UnauthorizedException(String reason) {
        super(reason);
    }

    public UnauthorizedException() {super();}
}
