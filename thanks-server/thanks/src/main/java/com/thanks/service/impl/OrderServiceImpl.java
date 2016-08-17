package com.thanks.service.impl;

import com.thanks.model.*;
import com.thanks.repository.OrderInfoRepository;
import com.thanks.repository.OrderRepository;
import com.thanks.repository.RestaurantOrderMenuRepository;
import com.thanks.repository.UserRepository;
import com.thanks.repository.redis.PushRepository;
import com.thanks.service.OrderService;
import com.thanks.util.AssertUtil;
import com.thanks.util.exception.TryMatchedOrderException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by micky on 2016. 7. 23..
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    OrderInfoRepository orderInfoRepository;

    @Autowired
    RestaurantOrderMenuRepository orderMenuRepository;

    @Autowired
    PushRepository pushRepository;

    @Autowired
    UserRepository userRepository;

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
    public List<OrderObject> getUserOrderList(User user, boolean isOrdered) {
//        ArrayList<List<OrderObject>> userBasket = new ArrayList<>();
//        for (OrderObject.OrderType t : OrderObject.OrderType.values()) {
//            userBasket.add(restaurantOrderRepository.findByOrderIdAndOrderYnAndObjectType(user.getId(), isOrdered, t.value));
//        }
        return orderRepository.findByOrderIdAndOrderYn(user.getId(),isOrdered);
    }

    @Transactional
    @Override
    public OrderObject toOrderList(User user, Long id) {
        OrderObject orderObject = orderRepository.findOne(id);
        orderObject.setOrderYn(true);
        orderObject.setUpdatedTime(Calendar.getInstance().getTime());
        return orderRepository.saveAndFlush(orderObject);
    }
    @Transactional
    @Override
    public OrderObject addRestaurantOrder(OrderObject orderObject) {
        orderRepository.save(orderObject);
        RestaurantOrder restaurantOrder = (RestaurantOrder) orderObject;
        for(RestaurantOrderMenu orderMenu : restaurantOrder.getMenuList())
            orderMenu.setRestaurantOrder(orderObject.getId());

        orderMenuRepository.save(restaurantOrder.getMenuList());
        return orderObject;
    }

    @Override
    public OrderInfo riderMatchedOrder(User rider) {
        return orderInfoRepository.findByRiderAndState(rider, OrderInfo.OrderState.MATCH);
    }

    @Override
    public List<OrderInfo> riderCompleteOrder(User rider) {
        return orderInfoRepository.findAllByRiderAndState(rider, OrderInfo.OrderState.COMPLETE);
    }


    @Transactional
    @Override
    public void toAllOrder(User user) {
        List<OrderObject> orderObjectList = orderRepository.findByOrderIdAndOrderYn(user.getId(), false);
        AssertUtil.state(orderObjectList.size() != 0, "No order in basket");
        List<Location> orderLocations = new ArrayList<>();

        StringBuilder sb = new StringBuilder();

        Date now = new Date();

        int price = 0;
        int deliveryPrice = 0;
        for (OrderObject data : orderObjectList) {
            price += data.getPrice() + data.getAddPrice();
            deliveryPrice += data.getAddPrice();
            sb.append(data.getComment()).append("/");

            double lat, lon;
            if(data instanceof Buy) {
                Buy order = (Buy) data;
                lat = order.getLat();
                lon = order.getLon();
            } else if (data instanceof Quick) {
                Quick order = (Quick) data;
                lat = order.getStartLat();
                lon = order.getStartLon();
            } else if(data instanceof Errand){
                Errand order = (Errand) data;
                lat = order.getLat();
                lon = order.getLon();
            } else {
                RestaurantOrder order = (RestaurantOrder) data;
                lat = order.getRestaurant().getLat();
                lon = order.getRestaurant().getLon();
            }

            orderLocations.add(new Location(lat, lon));
        }
        sb.deleteCharAt(sb.length() - 1);

        OrderInfo orderInfo = new OrderInfo(price, sb.toString(), orderObjectList.size(), user);
        orderInfo.setDeliveryPrice(deliveryPrice);
        orderInfo.setState(OrderInfo.OrderState.PENDING);
        orderInfo.setCreateTime(now);
        orderInfo.setUpdatedTime(now);

        orderInfo.setLocations(orderLocations);
        orderInfoRepository.saveAndFlush(orderInfo);

        for (OrderObject data : orderObjectList) {
            data.setOrderYn(true);
            data.setUpdatedTime(now);
            data.setOrderInfo(orderInfo.getId());
        }

        orderRepository.save(orderObjectList);

        pushRepository.pushOrderData(orderInfoToPushInformation(orderInfo));
    }

    @Override
    public List<OrderInfo> userOrderInfo(User user) {
        List<OrderInfo> o = orderInfoRepository.findAllByOrderIdOrderByUpdatedTimeDesc(user.getId());
//        List info = restaurantOrderRepository.getOrderInfo(user.getId());
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
        info.getItems();
        return info;
    }

    @Override
    public List<OrderInfo> getOrderByLocation(Double lat, Double lon, Double distance) {
        return orderInfoRepository.findAllByLocationAndState(lat, lon, distance);
    }

    @Override
    public void setOrderRider(User user, Long order) {
        OrderInfo oi = orderInfoRepository.findOne(order);

        // 이미 성사된 주문 혹은 완료된 주문은 주문을 받을 수 없게 한다
        if(oi.getState() == OrderInfo.OrderState.MATCH ||
                oi.getState() == OrderInfo.OrderState.COMPLETE) {
            throw new TryMatchedOrderException();
        }

        oi.setRider(user);
        oi.setState(OrderInfo.OrderState.MATCH);
        oi.setMatchDate(new Date());

        orderInfoRepository.save(oi);

        PushInformation pushInformation = createSelectNotificationPushInformation(oi.getOrder(), oi, "주문이 완료되었습니다.");
        pushRepository.pushSelect(pushInformation);
    }


    @Override
    public void orderComplete(User user, Long order) {
        OrderInfo oi = orderInfoRepository.findOne(order);

        if(user.getId().equals(oi.getRider().getId()) || user.getId().equals(oi.getOrder().getId())) {
            oi.setState(OrderInfo.OrderState.COMPLETE);
            orderInfoRepository.saveAndFlush(oi);

            PushInformation pushInformation = createSelectNotificationPushInformation(oi.getOrder(), oi, "주문이 완료되었습니다.");
            pushRepository.pushSelect(pushInformation);
        }
    }

    /**
     * 사용자에게 보낼 푸시 정보
     * @param target 대상 사용자
     * @param data 보낼 데이터
     * @return
     */
    private PushInformation createSelectNotificationPushInformation(User target, OrderInfo data, String notiMessage) {
        PushInformation info;
        AndroidNotificationData noti = new AndroidNotificationData();

        noti.setTitle(notiMessage);
        noti.setIcon("ic_launcher");
        noti.setBody(data.getComment());
        noti.setId(data.getId());
        info = new PushInformation<>(target.getPushTokens(), noti, null);
        return info;
    }

    /**
     * 주문을 넣고 라이더에게 푸시메시지를 전송한다
     * @param data
     * @return
     */
    private PushInformation orderInfoToPushInformation(OrderInfo data) {
        PushInformation info;
        AndroidNotificationData noti = new AndroidNotificationData();

        noti.setTitle("Thanks");
        noti.setIcon("ic_launcher");
        noti.setBody(data.getComment());
        noti.setId(data.getId());
        info = new PushInformation<>(data.getLocations(), noti, null);
        return info;
    }


}
