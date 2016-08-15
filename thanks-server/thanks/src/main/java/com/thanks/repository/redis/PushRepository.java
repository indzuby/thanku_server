package com.thanks.repository.redis;

import org.springframework.stereotype.Repository;

/**
 * Created by micky on 2016. 8. 8..
 */
public interface PushRepository  {

    public void pushData(double lat, double lon, String message);
}
