package com.thanks.service.impl;

import com.thanks.model.OrderObject;
import com.thanks.model.User;
import com.thanks.repository.OrderObjectRepository;
import com.thanks.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by micky on 2016. 7. 23..
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    OrderObjectRepository orderObjectRepository;

    @Override
    public List<OrderObject> findAll() {
        return orderObjectRepository.findAll();
    }

    @Override
    public OrderObject find(Long key) {
        return orderObjectRepository.findOne(key);
    }

    @Transactional
    @Override
    public OrderObject add(OrderObject data) {
        return orderObjectRepository.saveAndFlush(data);
    }

    @Transactional
    @Override
    public OrderObject update(Long key, OrderObject data) {
        data.setId(key);
        return orderObjectRepository.saveAndFlush(data);
    }

    @Transactional
    @Override
    public void remove(Long key) {
        orderObjectRepository.delete(key);
    }

    @Override
    public List<List<OrderObject>> getUserOrderList(User user, boolean isOrdered) {
        ArrayList<List<OrderObject>> userBasket = new ArrayList<>();
        for(OrderObject.OrderType t : OrderObject.OrderType.values()) {
            userBasket.add(orderObjectRepository.findByOrderIdAndOrderYnAndObjectType(user.getId(), false, t.value));
        }
        return userBasket;
    }
}
