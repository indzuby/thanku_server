package com.thanks.model;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by micky on 2016. 6. 21..
 */
@EqualsAndHashCode(callSuper = true)
@Data
@RequiredArgsConstructor
@Table
@Entity
public class Quick extends BaseModel {

    @Column
    private String start_lat;

    @Column
    private String start_lon;

    @Column
    private String start_addr;

    @Column
    private String end_lat;

    @Column
    private String end_lon;

    @Column
    private String end_addr;

    @Column
    private String order_msg;

    @Column
    private int price;

    @Column
    private int add_price;

    @Column
    private boolean match_yn;

    @Column
    private boolean reserv_yn;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private User order;

    @ManyToOne
    @JoinColumn(name = "rider_id")
    private User rider;

    @Temporal(TemporalType.TIMESTAMP)
    private Date match_date;

    @Temporal(TemporalType.TIMESTAMP)
    private Date reserv_date;
}
