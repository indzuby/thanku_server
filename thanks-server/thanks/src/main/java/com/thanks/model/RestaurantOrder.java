package com.thanks.model;

import lombok.Data;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToOne;

/**
 * Created by micky on 2016. 7. 23..
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
