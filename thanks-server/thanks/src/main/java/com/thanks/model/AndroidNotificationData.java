package com.thanks.model;

import lombok.Data;

/**
 * 안드로이드 푸시메시지에 나타날 푸시 메시지 정보
 * Created by micky on 2016. 8. 15..
 */
@Data
public class AndroidNotificationData extends NotificationData {
    private String title;
    private String body;
    private String icon;
    private Long id;
}
