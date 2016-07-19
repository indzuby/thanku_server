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
@AttributeOverrides({
        @AttributeOverride(name = "id", column = @Column),
        @AttributeOverride(name = "createTime", column = @Column),
        @AttributeOverride(name = "updatedTime", column = @Column)
})
@Entity
public class Errand extends BaseModel {

    @Column
    private int price;

    @Column
    private int add_price;

    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name = "order_id")
    private User order;

    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name = "rider_id")
    private User rider;

    @Column
    private String description;

    @Column
    private boolean reserv_yn;

    @Column
    private boolean match_yn;

    @Column
    private String lat;

    @Column
    private String lon;

    @Column
    private String address;

    @Temporal(TemporalType.TIMESTAMP)
    private Date reserv_date;

    @Temporal(TemporalType.TIMESTAMP)
    private Date match_date;
}
