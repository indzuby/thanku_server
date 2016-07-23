package com.thanks.service;

import com.thanks.model.Advertisement;

import java.util.List;

/**
 * Created by zuby on 2016. 7. 21..
 */
public interface AdvertisementService extends ServiceBase<Advertisement>{
    List<Advertisement> findByType(Advertisement.AdvertisementType type);
}
