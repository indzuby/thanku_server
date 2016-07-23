package com.thanks.model;

import lombok.*;

import javax.persistence.*;
import java.util.Date;


/**
 *
 * @author micky
 */
@EqualsAndHashCode(callSuper = true)
@Data
@RequiredArgsConstructor
@Entity
@Table
@DiscriminatorValue("Q")
public class Quick extends OrderObject {


    // 배달 시작 위치
    @Column(name = "start_lat")
    private double startLat;
    @Column(name = "start_lon")
    private double startLon;
    // 배달 시작 주소
    @Column(name = "start_addr")
    private String startAddr;

    // 배달 대상 위치
    @Column(name = "end_lat")
    private double endLat;
    @Column(name = "end_lon")
    private double endLon;
    // 배달 대상 주소
    @Column(name = "end_addr")
    private String endAddr;


    // 예약
    @Column(name = "reserv_yn")
    private boolean reservYn;


    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "reserv_date")
    private Date reservDate;
}
