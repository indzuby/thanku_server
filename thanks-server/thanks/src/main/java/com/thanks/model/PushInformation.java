package com.thanks.model;

import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

/**
 * Created by micky on 2016. 8. 15..
 */
@Data
@RequiredArgsConstructor
public class PushInformation {

    @NonNull
    private Double lat;

    @NonNull
    private Double lon;

    private Double distance = 1.0;
    private String unit = "km";

    @NonNull
    private NotificationData notification;

    @NonNull
    private Object data;

}
