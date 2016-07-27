package com.thanks.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
public class Restaurant extends BaseModel{

    @Column(nullable = false)
    String name;

    String businessHours;

    String url;

    int likeCount;

    int commentCount;

    int callCount;

    @Lob
    String description;


    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "category_restaurant", catalog = "thanks",
            joinColumns = {@JoinColumn(name = "restaurant_id",nullable = false)},
            inverseJoinColumns = {@JoinColumn(name = "category_id",nullable = false)})
    private Set<Category> categories = new HashSet<>();

    double lat;
    double lon;

    @JsonProperty
    @Transient
    List<RestaurantMenu> menuList;

    @JsonProperty
    @Transient
    List<RestaurantImage> imageList;

    @JsonProperty
    @Transient
    List<Review> reviewList;

    @JsonProperty
    @Transient
    double avgScore;
}
