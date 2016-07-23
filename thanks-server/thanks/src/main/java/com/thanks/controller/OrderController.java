package com.thanks.controller;

import com.thanks.form.OrderObjectForm;
import com.thanks.model.OrderInfo;
import com.thanks.model.OrderObject;
import com.thanks.model.User;
import com.thanks.service.OrderService;
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

    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    @ResponseBody
    public OrderObject getOrderInfo(@PathVariable Long id) {
        return orderService.find(id);
    }

    @RequestMapping(method = RequestMethod.POST, value="")
    @ResponseBody
    public OrderObject addOrder(@CurrentUser User user, @RequestBody OrderObjectForm orderObjectForm) {
        OrderObject orderObject = orderObjectForm.toOrderObject();
        orderObject.setOrder(user);
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

}
