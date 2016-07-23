package com.thanks.service.impl;

import com.thanks.service.DatetimeService;
import lombok.extern.slf4j.Slf4j;

import java.time.ZonedDateTime;

/**
 * Created by micky on 2016. 7. 23..
 */
@Slf4j
public class CurrentDatetimeService implements DatetimeService {

    @Override
    public ZonedDateTime getCurrentDateAndTime() {
        log.debug("get Now!");
        return ZonedDateTime.now();
    }
}
