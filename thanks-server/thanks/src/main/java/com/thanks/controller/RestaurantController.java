package com.thanks.controller;

import com.thanks.model.Category;
import com.thanks.model.Restaurant;
import com.thanks.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by zuby on 2016. 7. 22..
 */
@RestController
@RequestMapping("/api/restaurant")
public class RestaurantController {

    @Autowired
    RestaurantService restaurantService;

    @RequestMapping(method = RequestMethod.GET, value = "/categories")
    @ResponseBody
    public List<Category> findCategoryAll(){
        return restaurantService.findCategoryAll();
    }
    @RequestMapping(method = RequestMethod.GET, value = "/category/{category}")
    @ResponseBody
    public List<Restaurant> findRestaurantByCategory(@PathVariable Long category){
        return restaurantService.findByCategory(category);
    }
}
