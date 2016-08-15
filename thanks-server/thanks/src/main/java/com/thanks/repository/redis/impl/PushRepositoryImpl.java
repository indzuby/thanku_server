package com.thanks.repository.redis.impl;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.thanks.model.AndroidNotificationData;
import com.thanks.model.PushInformation;
import com.thanks.repository.redis.PushRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

/**
 * Created by micky on 2016. 8. 10..
 */
@Repository
public class PushRepositoryImpl implements PushRepository {

    @Autowired
    private RedisTemplate<String, String> template;

    private Gson gson = new Gson();

    @Override
    public void pushData(PushInformation information) {
        template.convertAndSend("push", gson.toJson(information));
    }
}
