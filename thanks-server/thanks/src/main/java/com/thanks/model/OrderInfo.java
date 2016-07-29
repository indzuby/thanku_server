package com.thanks.model;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.util.List;

/**
 * 실제 주문 정보
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

    public OrderInfo(long price, String comment, String orderDate, int count, User order) {
        this.price = price;
        this.comment = comment;
        this.orderDate = orderDate;
        this.count = count;
        this.order = order;
    }
}
