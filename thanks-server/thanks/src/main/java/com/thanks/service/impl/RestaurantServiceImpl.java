package com.thanks.service.impl;

import com.thanks.model.Category;
import com.thanks.model.Restaurant;
import com.thanks.repository.CategoryRepository;
import com.thanks.repository.RestaurantRepository;
import com.thanks.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by zuby on 2016. 7. 22..
 */
@Service
public class RestaurantServiceImpl implements RestaurantService {


    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    RestaurantRepository restaurantRepository;

    @Override
    public List<Category> findCategoryAll() {
        return categoryRepository.findAllByOrderByPriorityAsc();
    }

    @Override
    public List<Restaurant> findByCategory(Long id) {

        if(id <=0L)
            return restaurantRepository.findAll();
        return restaurantRepository.findByCategory(id);
//        return null;
    }

    @Override
    public List<Restaurant> findAll() {
        return restaurantRepository.findAll();
    }

    @Override
    public Restaurant find(Long key) {
        return restaurantRepository.findOne(key);
    }

    @Override
    public Restaurant add(Restaurant data) {
        return null;
    }

    @Override
    public Restaurant update(Long key, Restaurant data) {
        return null;
    }

    @Override
    public void remove(Long key) {

    }
}
