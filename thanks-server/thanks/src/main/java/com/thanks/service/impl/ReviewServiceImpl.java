package com.thanks.service.impl;

import com.thanks.model.OrderObject;
import com.thanks.model.Review;
import com.thanks.repository.ReviewRepository;
import com.thanks.service.OrderService;
import com.thanks.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by rlawn on 2016-07-27.
 */
@Service
public class ReviewServiceImpl implements ReviewService {
    @Autowired
    ReviewRepository reviewRepository;

    @Autowired
    OrderService orderService;

    @Override
    public List<Review> findAll() {
        return reviewRepository.findAll();
    }

    @Override
    public Review find(Long key) {
        return reviewRepository.findOne(key);
    }

    @Override
    public List<Review> findByOrderId(Long orderId) {
        return reviewRepository.findByOrderObjectIdOrderByScoreDesc(orderId);
    }

    @Override
    public Review add(Review data) {
        Date date = new Date();
        data.setCreateTime(date);
        data.setUpdatedTime(date);
        OrderObject orderObject = orderService.find(data.getOrderObjectId());
        Review review = reviewRepository.saveAndFlush(data);
        orderObject.setReview(review);
        orderService.update(orderObject.getId(),orderObject);
        return review;
    }

    @Override
    public Review update(Long key, Review data) {
        data.setId(key);
        data.setUpdatedTime(new Date());
        return reviewRepository.saveAndFlush(data);
    }

    @Override
    public void remove(Long key) {
        reviewRepository.delete(key);
    }
}
