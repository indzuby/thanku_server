package com.thanks.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.util.List;

/**
 * Created by micky on 2016. 7. 24..
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table
public class OrderInfo extends BaseModel{

    private long price;

    private String comment;

    private String orderDate;

    private int count;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "order_id")
    private User order;

    @OneToMany(mappedBy = "orderInfo", cascade = CascadeType.ALL)
    private List<OrderObject> items;
}
