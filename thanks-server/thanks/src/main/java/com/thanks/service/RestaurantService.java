package com.thanks.service;

import com.thanks.model.Category;
import com.thanks.model.Restaurant;

import java.util.List;

/**
 * Created by zuby on 2016. 7. 22..
 */

public interface RestaurantService extends ServiceBase<Restaurant>{
    List<Category> findCategoryAll();

    List<Restaurant> findByCategory(Long id);

}
