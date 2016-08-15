package com.thanks.service.impl;

import com.thanks.model.Advertisement;
import com.thanks.model.PushInformation;
import com.thanks.repository.AdvertisementRepository;
import com.thanks.repository.redis.PushRepository;
import com.thanks.service.AdvertisementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * Created by zuby on 2016. 7. 21..
 */
@Service
public class AdvertisementServiceImpl implements AdvertisementService {

    @Autowired
    AdvertisementRepository advertisementRepository;

    @Override
    public List<Advertisement> findByType(Advertisement.AdvertisementType type) {
        Date nowDate = new Date();
        return advertisementRepository.findByTypeAndStartTimeBeforeAndEndTimeAfterOrderByPriorityAsc(type,nowDate,nowDate);
    }

    @Override
    public List<Advertisement> findAll() {
        return advertisementRepository.findAll();
    }

    @Override
    public Advertisement find(Long key) {
        return advertisementRepository.findOne(key);
    }


    @Transactional
    @Override
    public Advertisement add(Advertisement data) {
        return advertisementRepository.saveAndFlush(data);
    }

    @Override
    public Advertisement update(Long key, Advertisement data) {
        data.setId(key);
        return advertisementRepository.saveAndFlush(data);
    }

    @Override
    public void remove(Long key) {
        advertisementRepository.delete(key);
    }
}
