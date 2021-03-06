package com.thanks.form;

import com.thanks.model.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;
import java.util.List;

/**
 * 주문 정보를 수신하기 위한 폼
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class OrderObjectForm extends BaseForm{

    // common

    public OrderObject.OrderType type;

    /**
     * 주문자 키
     */
    private Long order;

    private int price;

    private int addPrice;

    private double lon;

    private double lat;

    private String address;

    private String comment;

    // buy
    private String orderTel;

    private String receiverTel;


    // errand

    private boolean matchYn;

    private Date matchDate;

    //quick
    private double startLat;
    private double startLon;
    private String startAddr;
    private double endLat;
    private double endLon;
    private String endAddr;
    private boolean reservYn;
    private Date reservDate;

    //Restaurant
    private Long restaurantId;

    private List<RestaurantOrderMenu> menuList;


    public OrderObject toOrderObject() {
        OrderObject orderObject = modelMapper.map(this, type.type);

        if(orderObject instanceof RestaurantOrder) {
            Restaurant r = new Restaurant();
            r.setId(restaurantId);
            ((RestaurantOrder)orderObject).setRestaurant(r);
        }

        return orderObject;
    }
}
