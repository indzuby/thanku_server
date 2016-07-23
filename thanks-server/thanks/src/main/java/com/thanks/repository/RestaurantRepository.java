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
public interface RestaurantRepository extends JpaRepository<Restaurant,Long>{

    @Query("SELECT r from Restaurant r inner join r.categories c where c.id = (:category)")
    List<Restaurant> findByCategory(@Param("category") Long categoryId);
}
