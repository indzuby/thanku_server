package com.thanks.repository.redis;

import com.thanks.model.AndroidNotificationData;
import com.thanks.model.PushInformation;

/**
 * Created by micky on 2016. 8. 8..
 */
public interface PushRepository  {

    /**
     * 주문이 등록되었을 때 해당 위치의 라이더들에게 푸시를 전달한다
     * @param information
     */
    public void pushOrderData(PushInformation information);

    /**
     * 라이더가 주문을 접수 했을 때 발생하는 푸시를 전달한다
     * @param information
     */
    public void pushSelect(PushInformation information);
}
