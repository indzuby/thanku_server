package com.thanks.repository.redis;

import com.thanks.model.AndroidNotificationData;
import com.thanks.model.PushInformation;

/**
 * Created by micky on 2016. 8. 8..
 */
public interface PushRepository  {

    public void pushOrderData(PushInformation information);

    public void pushSelect(PushInformation information);
}
