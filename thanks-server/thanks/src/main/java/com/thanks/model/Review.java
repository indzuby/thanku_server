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
    private String comment;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User writer;

    @ColumnDefault("5")
    private int score;

    private long orderObjectId;

}
