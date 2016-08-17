package com.thanks.util.exception;

/**
 * Created by micky on 2016. 8. 17..
 */
public class TryMatchedOrderException extends RuntimeException {

    public static final String ALREADY_MATCHED = "이미 성사된 주문입니다.";

    public TryMatchedOrderException() {
        super(ALREADY_MATCHED);
    }

}
