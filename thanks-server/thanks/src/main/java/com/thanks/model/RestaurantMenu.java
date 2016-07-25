package com.thanks.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;

/**
 * Created by zuby on 2016. 7. 25..
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
public class RestaurantMenu extends BaseModel{
    String name;
    int price;
    String url;

    @ManyToOne
    @JoinColumn(name = "restaurant_id")
    Restaurant restaurant;
}
