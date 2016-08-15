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

    public PushInformation(Double lat, Double lon, NotificationData notification, Object data) {
        this.lat = lat;
        this.lon = lon;
        this.notification = notification;
        this.data = data;
    }

    public PushInformation(String target, NotificationData notification, Object data) {
        this.target = target;
        this.notification = notification;
        this.data = data;
    }

    private Double lat;
    private Double lon;

    private String target;

    private Double distance = 1.0;
    private String unit = "km";

    @NonNull
    private NotificationData notification;

    @NonNull
    private Object data;

}
