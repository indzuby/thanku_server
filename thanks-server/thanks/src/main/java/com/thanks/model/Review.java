package com.thanks.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;

/**
 * Created by zuby on 2016. 7. 25..
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
public class Review extends BaseModel {

    @Lob
    String comment;

    @ManyToOne
    @JoinColumn(name = "user_id")
    User user;

    @ColumnDefault("5")
    int score;

    long orderObjectId;

}
