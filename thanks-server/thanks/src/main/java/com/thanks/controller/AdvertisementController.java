package com.thanks.controller;

import com.thanks.model.Advertisement;
import com.thanks.service.AdvertisementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by zuby on 2016. 7. 21..
 */
@RestController
@RequestMapping("/api/adv")
public class AdvertisementController {
    @Autowired
    AdvertisementService advertisementService;

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public List<Advertisement> findByType(@RequestParam Advertisement.AdvertisementType type) {
        return advertisementService.findByType(type);
    }
}
