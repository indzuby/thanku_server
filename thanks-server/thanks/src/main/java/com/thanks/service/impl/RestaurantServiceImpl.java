package com.thanks.service.impl;

import com.thanks.model.*;
import com.thanks.repository.*;
import com.thanks.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by zuby on 2016. 7. 22..
 */
@Service
public class RestaurantServiceImpl implements RestaurantService {


    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    RestaurantRepository restaurantRepository;

    @Autowired
    RestaurantMenuRepository menuRepository;

    @Autowired
    RestaurantImageRepository imageRepository;

    @Autowired
    RestaurantOrderRepository restaurantOrderRepository;

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    RestaurantInfoRepository infoRepository;


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
    public List<Restaurant> findByCategoryAndDistance(Long id, double lat, double lon) {
        if(lat <=0 && lon<=0)
            return findByCategory(id);
        if(id<=0L)
            return restaurantRepository.findByDistance(lat,lon);
        return restaurantRepository.findByCategoryAndDistance(id,lat,lon);
    }

    @Override
    public List<Restaurant> findAll() {
        return restaurantRepository.findAll();
    }

    @Override
    public Restaurant find(Long key) {
        Restaurant restaurant = restaurantRepository.findOne(key);
        restaurant.setImageList(imageRepository.findByRestaurantIdOrderByPriorityAsc(key));
//        restaurant.setMenuList(menuRepository.findByRestaurantId(key));
        List<RestaurantOrder> list = restaurantOrderRepository.findByRestaurantId(key);
        double score = 0;
        int size = 0 ;
        for(RestaurantOrder order : list) {
            OrderObject object = orderRepository.findOne(order.getId());
            if(object.getReview()!=null) {
                score += object.getReview().getScore();
                size++;
            }
        }
        restaurant.setReviewCount(size);
        if(size>0)
            restaurant.setAvgScore(score / size);

        return restaurant;
    }

    @Override
    public RestaurantInfo findRestaurantInfo(Long id) {
        return infoRepository.findOne(id);
    }

    @Override
    public List<RestaurantMenu> findMenuListByRestaurantId(Long id) {
        return menuRepository.findByRestaurantId(id);
    }

    @Override
    public List<Review> findReviewListByRestaurantId(Long id) {
        List<RestaurantOrder> list = restaurantOrderRepository.findByRestaurantId(id);
        List<Review> reviews = new ArrayList<>();
        for(RestaurantOrder order : list) {
            OrderObject object = orderRepository.findOne(order.getId());
            if(object.getReview()!=null) {
                reviews.add(object.getReview());
            }
        }
        Collections.sort(reviews, new Comparator<Review>() {
            @Override
            public int compare(Review o1, Review o2) {
                return o2.getScore() - o1.getScore();
            }
        });
        return reviews;
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
