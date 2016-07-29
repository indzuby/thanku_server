package com.thanks.service;

import com.thanks.model.OrderInfo;
import com.thanks.model.OrderObject;
import com.thanks.model.User;

import java.util.List;

/**
 * Created by micky on 2016. 7. 23..
 */
public interface OrderService extends ServiceBase<OrderObject> {
    List<OrderObject> getUserOrderList(User user, boolean isOrdered);

    OrderObject toOrderList(User user, Long id);

    OrderObject addRestaurantOrder(OrderObject orderObject);

    void toAllOrder(User user);

    List<OrderInfo> userOrderInfo(User user);

    OrderInfo getInfo(Long id);

}
