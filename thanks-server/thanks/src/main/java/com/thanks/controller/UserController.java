package com.thanks.controller;

import com.thanks.form.EmailSignUpForm;
import com.thanks.form.SocialSignUpForm;
import com.thanks.model.OrderInfo;
import com.thanks.model.OrderObject;
import com.thanks.model.User;
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
import java.util.Date;
import java.util.List;

/**
 * Created by micky on 2016. 7. 17..
 */
@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    UserService userService;



    @RequestMapping(method = RequestMethod.GET, value = "/{user}")
    @ResponseBody
    public User findUser(@PathVariable Long user) {
        return userService.find(user);
    }

    @RequestMapping(method = RequestMethod.GET, value="/all")
    @ResponseBody
    public List<User> findAllUser() {
        return userService.findAll();
    }

    @RequestMapping(method=RequestMethod.POST, value="")
    @ResponseBody
    public User addUserByEmail(@RequestBody EmailSignUpForm user) {
        return userService.add(user.toUser());
    }


    @RequestMapping(method=RequestMethod.POST, value="/social")
    @ResponseBody
    public User addUserBySocial(@RequestBody SocialSignUpForm user) {
        return userService.add(user.toUser());
    }

    @RequestMapping(method = RequestMethod.GET, value="")
    @ResponseBody
    public User getMyInfo(@CurrentUser User user, @RequestParam(required = false, name = "t") String token) {
        userService.addPushToken(user, token);
        return user;
    }

    @RequestMapping(method= RequestMethod.GET, value="/basket")
    @ResponseBody
    public List<OrderObject> getBasket(@CurrentUser User user) {
        return userService.getUserBasket(user);
    }

    @RequestMapping(method= RequestMethod.GET, value="/order")
    @ResponseBody
    public List<OrderInfo> getOrderList(@CurrentUser User user) {
        return userService.getUserOrder(user);
    }

    @RequestMapping(method = RequestMethod.POST, value="/update")
    @ResponseBody
    public User update(@RequestBody User user) {
        user.setUpdatedTime(new Date());
        return userService.update(user.getId(),user);
    }

    @RequestMapping(method=RequestMethod.GET, value="/logout")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void logout(HttpServletRequest request, HttpServletResponse response, @CurrentUser User user, @RequestParam(name="t", required = false) String token) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication != null) {
            userService.removePushToken(user, token);
            new SecurityContextLogoutHandler().logout(request, response, authentication);
        }
    }
}
