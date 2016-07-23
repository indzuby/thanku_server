package com.thanks.util.provider;

import com.thanks.service.DatetimeService;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import org.springframework.data.auditing.DateTimeProvider;

import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * Created by micky on 2016. 7. 23..
 */
@Data
public class AuditingDateTimeProvider implements DateTimeProvider {

    private final DatetimeService datetimeService;

    @Override
    public Calendar getNow() {
        return GregorianCalendar.from(datetimeService.getCurrentDateAndTime());
    }
}
