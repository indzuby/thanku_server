package com.thanks.model;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.util.Date;
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
        // 라이더 대기
        PENDING,
        // 라이더 연결
        MATCH,
        // 주문 완료
        COMPLETE;
    }

    private long price;

    private long deliveryPrice;

    private String comment;

    private int count;

    /**
     * 라이더
     */
    @ManyToOne
    @JoinColumn(name = "rider_id")
    private User rider;


    /**
     * 계약 성사 시간
     */
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "match_date")
    private Date matchDate;


    /**
     * 주문 상태
     */
    @Enumerated(EnumType.STRING)
    private OrderState state;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "order_id")
    private User order;

    @OneToMany(mappedBy = "orderInfo", cascade = CascadeType.ALL)
    private List<OrderObject> items;

    @ElementCollection
    @CollectionTable(name="OrderLocation", joinColumns = @JoinColumn(name="order_id"))
    @Column(name="location")
    private List<Location> locations;

    public OrderInfo(long price, String comment, int count, User order) {
        this.price = price;
        this.comment = comment;
        this.count = count;
        this.order = order;
    }
}
