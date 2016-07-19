package com.thanks.util;

import org.springframework.util.Assert;

/**
 * Created by micky on 2016. 7. 18..
 */
public class AssertUtil extends Assert {

    public static void isNull(Object object, RuntimeException exception) {
        if (null != object) {
            throw exception;
        }
    }

    public static void notNull(Object object, RuntimeException exception) {
        if (null == object) {
            throw exception;
        }
    }

    public static void isTrue(boolean expression, RuntimeException exception) {
        if (!expression) {
            throw exception;
        }
    }

    public static void isFalse(boolean expression, RuntimeException exception) {
        if (expression) {
            throw exception;
        }
    }

    public static void isFalse(boolean expression, String message) {
        if (expression) {
            throw new IllegalArgumentException(message);
        }
    }

    public static void isFalse(boolean expression) {
        isFalse(expression, "[Assertion failed] - this expression must be true");
    }

}
