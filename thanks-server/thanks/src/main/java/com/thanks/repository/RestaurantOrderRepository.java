package com.thanks.repository;

import com.thanks.model.RestaurantOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by rlawn on 2016-07-27.
 */
@Repository
public interface RestaurantOrderRepository extends JpaRepository<RestaurantOrder,Long>{
    List<RestaurantOrder> findByRestaurantId(Long id);
}
