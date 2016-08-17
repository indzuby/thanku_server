package com.thanks.service.impl;

import com.thanks.model.OrderInfo;
import com.thanks.model.OrderObject;
import com.thanks.model.User;
import com.thanks.repository.UserRepository;
import com.thanks.service.OrderService;
import com.thanks.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by micky on 2016. 7. 17..
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    OrderService orderService;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public List findAll() {
        return null;
    }

    @Override
    public User find(Long key) {
        return userRepository.findOne(key);
    }

    @Transactional
    @Override
    public User add(User data) {
        if(data.getPassword() != null) {
            data.setPassword(bCryptPasswordEncoder.encode(data.getPassword()));
        }
        return userRepository.saveAndFlush(data);
    }

    @Transactional
    @Override
    public User update(Long key, User data) {

        data.setId(key);
        return userRepository.saveAndFlush(data);
    }

    @Transactional
    @Override
    public void remove(Long key) {
        userRepository.delete(key);
    }

    @Override
    public User findById(String userId) {
        return userRepository.findOne(Long.parseLong(userId));
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public User findByPhone(String phone) {
        return userRepository.findByPhone(phone);
    }

    @Override
    public User findBySocial(String token) {
        return userRepository.findBySocialAccessToken(token);
    }

    @Override
    public List<OrderObject> getUserBasket(User user) {
        return orderService.getUserOrderList(user, false);
    }

    @Override
    public List<OrderInfo> getUserOrder(User user) {
        return orderService.userOrderInfo(user);
    }

    @Override
    public void addPushToken(User u, String token) {
        if(token == null || token.isEmpty()) return;

        if(!u.getPushTokens().contains(token)) {
            u.getPushTokens().add(token);
        }

        userRepository.saveAndFlush(u);
    }

    @Override
    public void removePushToken(User u, String token) {
        if(token == null) return;

        u.getPushTokens().remove(token);

        userRepository.saveAndFlush(u);
    }
}
