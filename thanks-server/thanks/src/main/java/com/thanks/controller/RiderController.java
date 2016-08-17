package com.thanks.controller;

import com.thanks.form.EmailSignUpForm;
import com.thanks.model.OrderInfo;
import com.thanks.model.OrderObject;
import com.thanks.model.User;
import com.thanks.service.OrderService;
import com.thanks.service.UserService;
import com.thanks.util.annotation.CurrentUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by micky on 2016. 7. 23..
 */
@RestController
@RequestMapping("/api/rider")
public class RiderController {

    @Autowired
    private UserService userService;

    @Autowired
    private OrderService orderService;

    @RequestMapping(method= RequestMethod.POST, value="")
    @ResponseBody
    public User addRiderByEmail(@RequestBody EmailSignUpForm user) {
        return userService.add(user.toRider());
    }


    @RequestMapping(method = RequestMethod.GET, value="")
    @ResponseBody
    public User getMyInfo(@CurrentUser User user, @RequestParam(required = false, name = "t") String token) {
        userService.addPushToken(user, token);
        return user;
    }

    @RequestMapping(method = RequestMethod.POST, value="/update")
    @ResponseBody
    public User update(@RequestBody User user) {
        return userService.update(user.getId(),user);
    }

    @RequestMapping(method=RequestMethod.GET, value="/logout")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void logout(HttpServletRequest request, HttpServletResponse response
            , @CurrentUser User user, @RequestParam(name="t", required = false) String token) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication != null) {
            userService.removePushToken(user, token);
            new SecurityContextLogoutHandler().logout(request, response, authentication);
        }
    }

    @RequestMapping(method=RequestMethod.GET, value="/complete")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public List<OrderInfo> riderComplete(@CurrentUser User user) {
        return orderService.riderCompleteOrder(user);
    }

    @RequestMapping(method=RequestMethod.GET, value="/incomplete")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public OrderInfo riderMatch(@CurrentUser User user) {
        return orderService.riderMatchedOrder(user);
    }
}
