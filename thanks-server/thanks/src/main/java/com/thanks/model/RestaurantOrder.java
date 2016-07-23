package com.thanks.model;

import lombok.Data;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToOne;

/**
 * 레스토랑의 주문
 */
@Data
@DiscriminatorValue("R")
@Entity
public class RestaurantOrder extends OrderObject {

    /**
     * 식당이 사라지면 같이 사라짐
     */
    @OneToOne(orphanRemoval = true)
    Restaurant restaurant;
}
