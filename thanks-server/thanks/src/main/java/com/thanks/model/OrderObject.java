package com.thanks.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;


/**
 * Created by micky on 2016. 7. 23..
 */
@Data
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "object_type")
public abstract class OrderObject extends BaseModel {

    public enum OrderType {
        /**
         * 퀵
         */
        QUICK("Q", Quick.class)
        /**
         * 생활편의
         */
        , ERRAND("E", Errand.class)
        /**
         * 맛집
         */
        , RESTAURANT("R", RestaurantOrder.class)
        /**
         * 사다주기
         */
        , BUY("B", Buy.class);

        public final String value;
        public final Class<? extends OrderObject> type;
        OrderType(String value, Class<? extends OrderObject> type) {
            this.value = value;
            this.type = type;
        }

        @Override
        public String toString() {
            return this.name();
        }

        @JsonCreator
        public OrderType fromText(String s) {
            return OrderType.valueOf(s.toUpperCase());
        }
    }

    @Transient
    private OrderType type;

    /**
     * 주문 했는지
     * false 일 경우 장바구니에만 등록
     */
    @Column(name="order_yn")
    private boolean orderYn;

    /**
     * 주문자
     */
    @ManyToOne
    @JoinColumn(name = "order_id")
    private User order;

    /**
     * 라이더
     */
    @ManyToOne
    @JoinColumn(name = "rider_id")
    private User rider;

    /**
     * 가격
     */
    @Column
    private int price;

    /**
     * 추가금
     */
    @Column(name = "addPrice")
    private int addPrice;

    /**
     * 계약 성사 확인
     */
    @Column(name="match_yn")
    private boolean matchYn;

    /**
     * 계약 성사 시간
     */
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="match_date")
    private Date matchDate;

    @Column(name="object_type", nullable = false, updatable=false, insertable = false)
    private String objectType;

    @Lob
    private String comment;
}
