package com.thanks.controller;

import com.thanks.model.Category;
import com.thanks.model.Restaurant;
import com.thanks.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

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
    public List<Restaurant> findRestaurantByCategory(@PathVariable Long category,@RequestParam Map<String, String> params){
        double lat = Double.parseDouble(params.get("lat"));
        double lon = Double.parseDouble(params.get("lon"));
        return restaurantService.findByCategoryAndDistance(category,lat,lon);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    @ResponseBody
    public Restaurant find(@PathVariable Long id){
        return restaurantService.find(id);
    }
}
