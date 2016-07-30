package com.thanks.repository;

import com.thanks.model.RestaurantOrderMenu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by rlawn on 2016-07-29.
 */
@Repository
public interface RestaurantOrderMenuRepository extends JpaRepository<RestaurantOrderMenu,Long>{
}
