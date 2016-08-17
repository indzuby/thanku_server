package com.thanks.model;

import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.util.List;

/**
 * Created by micky on 2016. 8. 15..
 */
@Data
@RequiredArgsConstructor
public class PushInformation<T> {

    /**
     *
     * @param target 대상의 정보 (gcm 토큰 혹은 위치 정보(lat, lon 페어))
     * @param notification
     * @param data
     */
    public PushInformation(List<T> target, NotificationData notification, Object data) {
        this.target = target;
        this.notification = notification;
        this.data = data;
    }

    private Double lat;
    private Double lon;

    private List<T> target;

    private Double distance = 10000.0;
    private String unit = "km";

    @NonNull
    private NotificationData notification;

    @NonNull
    private Object data;

}
