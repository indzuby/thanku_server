package com.thanks.service;

import com.thanks.model.*;

import java.util.List;

/**
 * Created by zuby on 2016. 7. 22..
 */

public interface RestaurantService extends ServiceBase<Restaurant>{
    List<Category> findCategoryAll();

    List<Restaurant> findByCategory(Long id);

    List<Restaurant> findByCategoryAndDistance(Long id, double lat, double lon);

    List<Review> findReviewListByRestaurantId(Long id);

    List<RestaurantMenu> findMenuListByRestaurantId(Long id);

    RestaurantInfo findRestaurantInfo(Long id);
}
