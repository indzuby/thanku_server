package com.thanks.model;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by micky on 2016. 7. 24..
 */
@EqualsAndHashCode(callSuper = true)
@Data
@RequiredArgsConstructor
@Entity
@Table
@JsonAutoDetect
public class OrderInfo extends BaseModel{

    public enum OrderState {
        PENDING,MATCH,COMPLETE;
    }

    private long price;

    private long deliveryPrice;

    private long totalPrice;

    private String comment;

    private String orderDate;

    private int count;

    @Enumerated(EnumType.STRING)
    private OrderState state ;

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
