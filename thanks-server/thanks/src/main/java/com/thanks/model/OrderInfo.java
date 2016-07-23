package com.thanks.model;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by micky on 2016. 7. 24..
 */
@Data
@NoArgsConstructor
@Entity
@Table
@JsonAutoDetect
public class OrderInfo extends BaseModel{

    private long price;

    private String comment;

    private String orderDate;

    private int count;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "order_id")
    private User order;

    @OneToMany(mappedBy = "orderInfo", cascade = CascadeType.ALL)
    private List<OrderObject> items;

    @JsonProperty
    @Transient
    private List<List<OrderObject>> groupItems;

    public OrderInfo setGroupItems() {
        int i;
        ArrayList<List<OrderObject>> group = new ArrayList<>();
        group.add(new ArrayList<>());
        group.add(new ArrayList<>());
        group.add(new ArrayList<>());
        group.add(new ArrayList<>());

        for(OrderObject obj : items) {
            for (i = 0; i < OrderObject.OrderType.values().length; i++) {
                OrderObject.OrderType type = OrderObject.OrderType.values()[i];
                if (type.value.equals(obj.getObjectType())) {
                    break;
                }
            }

            group.get(i).add(obj);
        }
        setGroupItems(group);
        return this;
    }

    public OrderInfo(long price, String comment, String orderDate, int count, User order) {
        this.price = price;
        this.comment = comment;
        this.orderDate = orderDate;
        this.count = count;
        this.order = order;
    }
}
