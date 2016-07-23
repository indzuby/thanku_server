package com.thanks.service.impl;

import com.thanks.service.DatetimeService;

import java.time.ZonedDateTime;

/**
 * Created by micky on 2016. 7. 23..
 */
public class CurrentDatetimeServcie implements DatetimeService {

    @Override
    public ZonedDateTime getCurrentDateAndTime() {
        return ZonedDateTime.now();
    }
}
