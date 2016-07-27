package com.thanks.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;

/**
 * Created by rlawn on 2016-07-27.
 */

@EqualsAndHashCode(callSuper = true)
@Data
@RequiredArgsConstructor
@Table
@AttributeOverrides({
        @AttributeOverride(name = "id", column = @Column),
        @AttributeOverride(name = "createTime", column = @Column),
        @AttributeOverride(name = "updatedTime", column = @Column)
})
@Entity
public class RestaurantOrderMenu extends BaseModel {
    @ManyToOne
    @JoinColumn(name = "order_id")
    RestaurantOrder restaurantOrder;

    @ManyToOne
    @JoinColumn(name = "menu_id")
    RestaurantMenu restaurantMenu;

    int count;

    int price;

}
