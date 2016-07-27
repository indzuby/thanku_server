package com.thanks.service;

import com.thanks.model.OrderInfo;
import com.thanks.model.OrderObject;
import com.thanks.model.User;

import java.util.List;

/**
 * Created by micky on 2016. 7. 17..
 */
public interface UserService extends ServiceBase<User> {

    User findById(String userId);

    User findByEmail(String username);

    User findByPhone(String username);

    List<OrderObject> getUserBasket(User user);

    List<OrderInfo> getUserOrder(User user);
}
