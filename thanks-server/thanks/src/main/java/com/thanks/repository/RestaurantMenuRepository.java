package com.thanks.repository;

import com.thanks.model.RestaurantMenu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by rlawn on 2016-07-27.
 */
@Repository
public interface RestaurantMenuRepository extends JpaRepository<RestaurantMenu,Long>{
    List<RestaurantMenu> findByRestaurantId(Long id);
}
