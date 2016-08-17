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

    /**
     * 장바구니에 있는 모든 주문 정보를 실제 주문 처리한다
     * @param user
     */
    void toAllOrder(User user);

    List<OrderInfo> userOrderInfo(User user);

    OrderInfo getInfo(Long id);

    List<OrderInfo> getOrderByLocation(Double lat, Double lon, Double distance);

    /**
     * 라이더가 주문을 접수한다
     * @param user 현재 접속 중인 라이더 정보 혹은 사용자 정보
     * @param order 주문 정보
     */
    void setOrderRider(User user, Long order);

    OrderInfo riderMatchedOrder(User rider);

    List<OrderInfo> riderCompleteOrder(User rider);

    /**
     * 주문을 완료한다
     * @param user 현재 접속 중인 라이더 정보 혹은 사용자 정보
     * @param order
     */
    void orderComplete(User user, Long order);
}
