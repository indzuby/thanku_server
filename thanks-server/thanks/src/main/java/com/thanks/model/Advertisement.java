package com.thanks.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by zuby on 2016. 7. 21..
 */
@EqualsAndHashCode(callSuper = true)
@Data
@RequiredArgsConstructor
@Table
@Entity
public class Advertisement extends BaseModel{

    public enum AdvertisementType {
        START,BANNER,POPUP,NATIVE
    }


    @Column
    private String url;

    @Enumerated(EnumType.STRING)
    private AdvertisementType type;

    @Column
    private Date startTime;

    @Column
    private Date endTime;

    @Column
    private int priority;

}
