package com.thanks.repository;

import com.thanks.model.RestaurantInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by rlawn on 2016-07-29.
 */
@Repository
public interface RestaurantInfoRepository extends JpaRepository<RestaurantInfo,Long> {
}
