package com.thanks.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

/**
 * 레스토랑의 주문
 * @author micky
 */
@Data
@DiscriminatorValue("R")
@Entity
public class RestaurantOrder extends OrderObject {

    /**
     * 식당이 사라지면 같이 사라짐
     */
    @OneToOne(orphanRemoval = true)
    @JoinColumn(name = "restaurant_id")
    private Restaurant restaurant;


    @OneToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "order_id")
    private List<RestaurantOrderMenu> menuList;

    private int price;
}
