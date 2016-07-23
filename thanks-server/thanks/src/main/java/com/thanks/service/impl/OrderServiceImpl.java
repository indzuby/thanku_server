package com.thanks.service.impl;

import com.thanks.model.OrderInfo;
import com.thanks.model.OrderObject;
import com.thanks.model.User;
import com.thanks.repository.OrderInfoRepository;
import com.thanks.repository.OrderRepository;
import com.thanks.service.OrderService;
import com.thanks.util.AssertUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by micky on 2016. 7. 23..
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    OrderInfoRepository orderInfoRepository;

    @Override
    public List<OrderObject> findAll() {
        return orderRepository.findAll();
    }

    @Override
    public OrderObject find(Long key) {
        return orderRepository.findOne(key);
    }

    @Transactional
    @Override
    public OrderObject add(OrderObject data) {
        return orderRepository.saveAndFlush(data);
    }

    @Transactional
    @Override
    public OrderObject update(Long key, OrderObject data) {
        data.setId(key);
        return orderRepository.saveAndFlush(data);
    }

    @Transactional
    @Override
    public void remove(Long key) {
        orderRepository.delete(key);
    }

    @Override
    public List<List<OrderObject>> getUserOrderList(User user, boolean isOrdered) {
        ArrayList<List<OrderObject>> userBasket = new ArrayList<>();
        for(OrderObject.OrderType t : OrderObject.OrderType.values()) {
            userBasket.add(orderRepository.findByOrderIdAndOrderYnAndObjectType(user.getId(), isOrdered, t.value));
        }
        return userBasket;
    }

    @Override
    public OrderObject toOrderList(User user, Long id) {
        OrderObject orderObject = orderRepository.getOne(id);
        orderObject.setOrderYn(true);
        orderObject.setUpdatedTime(Calendar.getInstance().getTime());
        return orderRepository.saveAndFlush(orderObject);
    }

    @Transactional
    @Override
    public void toAllOrder(User user) {
        List<OrderObject> orderObjectList = orderRepository.findByOrderIdAndOrderYn(user.getId(), false);
        AssertUtil.state(orderObjectList.size() != 0, "No order in basket");

        StringBuilder sb = new StringBuilder();
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

        Date now = new Date();

        int price = 0;
        for (OrderObject obj : orderObjectList) {
            price += obj.getPrice() + obj.getAddPrice();
            sb.append(obj.getComment()).append("/");
        }
        sb.deleteCharAt(sb.length()-1);
        OrderInfo orderInfo = new OrderInfo(price, sb.toString(), df.format(now), orderObjectList.size(), user);
        orderInfo.setCreateTime(now);
        orderInfo.setUpdatedTime(now);
        orderInfoRepository.saveAndFlush(orderInfo);

        for (OrderObject obj : orderObjectList) {
            obj.setOrderYn(true);
            obj.setUpdatedTime(now);
            obj.setOrderInfo(orderInfo.getId());
        }

        orderRepository.save(orderObjectList);
    }

    @Override
    public List<OrderInfo> userOrderInfo(User user) {
        List<OrderInfo> o = orderInfoRepository.findAllByOrderIdOrderByUpdatedTimeDesc(user.getId());
//        List info = orderRepository.getOrderInfo(user.getId());
//
//        for (int i = 0; i < info.size(); i++) {
//            Object[] in = (Object[])info.get(i);
//            OrderInfo orderInfo = new OrderInfo();
//            orderInfo.setPrice(((BigDecimal)in[0]).longValue());
//            orderInfo.setCount(((BigInteger)in[1]).intValue());
//            orderInfo.setComment(in[2].toString());
//            orderInfo.setOrderDate(in[3].toString());
//            o.add(orderInfo);
//            //orderInfo.set
//        }
        return o;
    }

    @Override
    public OrderInfo getInfo(Long id) {
        OrderInfo info = orderInfoRepository.findOne(id);

        ArrayList<List<OrderObject>> groupItem = new ArrayList<>();
        for(OrderObject.OrderType t : OrderObject.OrderType.values()) {
            groupItem.add(orderRepository.findByOrderYnAndObjectTypeAndOrderInfo(true, t.value, id));
        }
        info.setGroupItems(groupItem);
        return info;
    }
}
