package com.thanks.repository;

import com.thanks.model.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by zuby on 2016. 7. 22..
 */
@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {

    @Query("SELECT r from Restaurant r inner join r.categories c where c.id = (:category) order by r.callCount")
    List<Restaurant> findByCategory(@Param("category") Long categoryId);

    @Query("SELECT r from Restaurant r inner join r.categories c where c.id = (:category)" +
            "and 111.1111 * DEGREES(ACOS(COS(RADIANS(:lat)) * COS(RADIANS(r.lat)) * COS(RADIANS(:lon-r.lon)) + SIN(RADIANS(:lat)) * SIN(RADIANS(r.lat)))) <=2 order by r.callCount desc")
    List<Restaurant> findByCategoryAndDistance(@Param("category") Long categoryId,@Param("lat") Double lat,@Param("lon") Double lon);

    @Query("SELECT r from Restaurant r where " +
            "111.1111 * DEGREES(ACOS(COS(RADIANS(:lat)) * COS(RADIANS(r.lat)) * COS(RADIANS(:lon-r.lon)) + SIN(RADIANS(:lat)) * SIN(RADIANS(r.lat)))) <=2 order by r.callCount desc")
    List<Restaurant> findByDistance(@Param("lat")Double lat, @Param("lon")Double lon);

}
