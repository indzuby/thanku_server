package com.thanks.controller;

import com.thanks.form.EmailSignUpForm;
import com.thanks.model.User;
import com.thanks.service.UserService;
import com.thanks.util.annotation.CurrentUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by micky on 2016. 7. 17..
 */
@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    UserService userService;


    @RequestMapping(method = RequestMethod.GET, value = "/user/{user}")
    @ResponseBody
    public User findUser(@PathVariable Long user) {
        return userService.find(user);
    }

    @RequestMapping(method = RequestMethod.GET, value="/users/")
    @ResponseBody
    public List<User> findAllUser() {
        return userService.findAll();
    }

    @RequestMapping(method=RequestMethod.POST, value="/user")
    @ResponseBody
    public User addUserByEmail(@RequestBody EmailSignUpForm user) {
        return userService.add(user.toUser());
    }

    @RequestMapping(method=RequestMethod.POST, value="/rider")
    @ResponseBody
    public User addRiderByEmail(@RequestBody EmailSignUpForm user) {
        return userService.add(user.toRider());
    }


    @RequestMapping(method=RequestMethod.GET, value="/mine")
    @ResponseBody
    public User getMyInfo(@CurrentUser User user) {
        return user;
    }

}
