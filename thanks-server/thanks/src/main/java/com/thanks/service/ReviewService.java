package com.thanks.service;

import com.thanks.model.Review;

import java.util.List;

/**
 * Created by rlawn on 2016-07-27.
 */
public interface ReviewService extends ServiceBase<Review> {
    List<Review> findByOrderId(Long orderId);
}
