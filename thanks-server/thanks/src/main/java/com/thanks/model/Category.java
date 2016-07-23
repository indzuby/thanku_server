package com.thanks.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Created by zuby on 2016. 7. 22..
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
public class Category extends BaseModel{
    //한식&중식 일&돈까스&회 치킨&피자 보쌈&족발 야식 탕&찜 도시락 프렌차이즈 카페&디저트 편의점
    public enum CategoryType{
        KOFOOD,JPFOOD,CHICKEN,PIGFOOD,NIGHTFOOD,SOUPFOOD,LUNCHFOOD,FRANCHISE,CAFE,CONVENIENCE
    }


    @Column(nullable = false)
    private String name;


    @Column
    private int priority;

    @Enumerated(EnumType.STRING)
    private CategoryType type;
}
