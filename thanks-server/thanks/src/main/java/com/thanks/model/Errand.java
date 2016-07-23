package com.thanks.model;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

/**
 * 심부름
 */
@EqualsAndHashCode(callSuper = true)
@Data
@RequiredArgsConstructor
@Entity
@Table
@DiscriminatorValue("E")
public class Errand extends OrderObject {


    /**
     * 예약
     */
    @Column(name = "reserv_yn")
    private boolean reservYn;

    @Column(name = "match_yn")
    private boolean matchYn;

    @Column
    private double lat;

    @Column
    private double lon;

    @Column
    private String address;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "reserv_date")
    private Date reservDate;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="match_date")
    private Date matchDate;
}
