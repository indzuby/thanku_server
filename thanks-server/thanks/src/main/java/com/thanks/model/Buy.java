package com.thanks.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by micky on 2016. 7. 23..
 */
@EqualsAndHashCode(callSuper = true)
@Data
//@RequiredArgsConstructor
@NoArgsConstructor
@Entity
@Table
@DiscriminatorValue("B")
public class Buy extends OrderObject {

    @Column(nullable = false, length = 20, name="order_tel")
    private String orderTel;

    @Column(nullable = false, length = 20, name="receiver_tel")
    private String receiverTel;

    @Column(nullable = false, length = 50)
    private String address;

    private double lat;
    private double lon;
}
