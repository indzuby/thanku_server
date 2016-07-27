package com.thanks.model;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by micky on 2016. 6. 21..
 */
@Data
//@EntityListeners(AuditingEntityListener.class)
@MappedSuperclass
public abstract class BaseModel {

    @Id
    @GeneratedValue
    @Column(unique = true, nullable = false)
    protected Long id;

    @Column
    @CreatedDate
    protected Date createTime;

    @Column
    @LastModifiedDate
    protected Date updatedTime;

    public Date getCreateTime() {
        if(createTime == null) {
            createTime = new Date();
        }

        return createTime;
    }

    public Date getUpdatedTime() {
        if (updatedTime == null) {
            updatedTime = new Date();
        }
        return updatedTime;
    }
}
