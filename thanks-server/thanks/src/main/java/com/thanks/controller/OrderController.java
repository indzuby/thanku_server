package com.thanks.controller;

import com.thanks.form.OrderObjectForm;
import com.thanks.model.OrderInfo;
import com.thanks.model.OrderObject;
import com.thanks.model.Review;
import com.thanks.model.User;
import com.thanks.service.OrderService;
import com.thanks.service.ReviewService;
import com.thanks.util.annotation.CurrentUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 *
 * @author micky
 */
@RestController
@RequestMapping("/api/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    ReviewService reviewService;


    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    @ResponseBody
    public OrderInfo getOrderInfo(@PathVariable Long id) {

        return orderService.getInfo(id);
    }

    @RequestMapping(method = RequestMethod.POST, value="")
    @ResponseBody
    public OrderObject addOrder(@CurrentUser User user, @RequestBody OrderObjectForm orderObjectForm) {
        OrderObject orderObject = orderObjectForm.toOrderObject();
        orderObject.setOrder(user);
        if(orderObject.getType() == OrderObject.OrderType.RESTAURANT) {
            return orderService.addRestaurantOrder(orderObject);
        }else
            return orderService.add(orderObject);
    }

    @RequestMapping(method = RequestMethod.PUT, value="/{id}")
    @ResponseBody
    public OrderObject updateOrder(@PathVariable Long id, @RequestBody OrderObjectForm orderObjectForm) {
        return orderService.update(id, orderObjectForm.toOrderObject());
    }

    @RequestMapping(method = RequestMethod.PUT, value="/{id}/ordering")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void toOrder(@CurrentUser User user, @PathVariable Long id) {
        orderService.toOrderList(user, id);
    }

    @RequestMapping(method = RequestMethod.PUT, value="/ordering")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void toEveryOrder(@CurrentUser User user) {
        orderService.toAllOrder(user);
    }

    @RequestMapping(method = RequestMethod.DELETE, value="/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remove(@PathVariable Long id) {
        orderService.remove(id);
    }

    @RequestMapping(method = RequestMethod.POST, value="/review")
    @ResponseBody
    public Review addReview(@CurrentUser User user, @RequestBody Review review) {
        review.setWriter(user);
        return reviewService.add(review);
    }
    @RequestMapping(method = RequestMethod.PUT, value="/review")
    @ResponseBody
    public Review editReview(@CurrentUser User user, @RequestBody Review review) {
        review.setWriter(user);
        return reviewService.update(review.getId(),review);
    }

    @RequestMapping(method=RequestMethod.GET, value="/location")
    @ResponseBody
    public List<OrderObject> getOrderByLocation(@RequestParam Double lat, @RequestParam Double lon) {
        return orderService.getOrderByLocation(lat, lon);
    }

    @RequestMapping(method=RequestMethod.GET, value="/select/{order}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void setOrderRider(@CurrentUser User user, @PathVariable Long order) {
        if(user.getType() == User.UserType.RIDER) {
            boolean success = orderService.setOrderRider(user, order);
            if(!success) throw new RuntimeException();
        }
    }

    @RequestMapping(method=RequestMethod.GET, value="/complete/{order}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void orderComplete(@CurrentUser User user, @PathVariable Long order) {
        orderService.orderComplete(user, order);
    }

}
