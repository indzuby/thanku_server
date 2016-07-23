package com.thanks.form;

import com.thanks.model.OrderObject;
import com.thanks.model.Restaurant;
import com.thanks.model.RestaurantOrder;
import com.thanks.model.User;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * Created by micky on 2016. 7. 23..
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

    private int add_price;

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
    private Long restaurant;


    public OrderObject toOrderObject() {
        OrderObject orderObject = modelMapper.map(this, type.type);

        if(orderObject instanceof RestaurantOrder) {
            Restaurant r = new Restaurant();
            r.setId(restaurant);
            ((RestaurantOrder)orderObject).setRestaurant(r);
        }

        return orderObject;
    }
}
