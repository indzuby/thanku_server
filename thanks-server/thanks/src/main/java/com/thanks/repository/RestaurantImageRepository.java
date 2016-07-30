package com.thanks.repository;

import com.thanks.model.RestaurantImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by rlawn on 2016-07-27.
 */
@Repository
public interface RestaurantImageRepository extends JpaRepository<RestaurantImage,Long>{
    List<RestaurantImage> findByRestaurantIdOrderByPriorityAsc(Long id);
}
