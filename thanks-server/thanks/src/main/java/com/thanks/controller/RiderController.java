package com.thanks.controller;

import com.thanks.form.EmailSignUpForm;
import com.thanks.model.User;
import com.thanks.service.UserService;
import com.thanks.util.annotation.CurrentUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * Created by micky on 2016. 7. 23..
 */
@RestController
@RequestMapping("/api/rider")
public class RiderController {

    @Autowired
    private UserService userService;

    @RequestMapping(method= RequestMethod.POST, value="")
    @ResponseBody
    public User addRiderByEmail(@RequestBody EmailSignUpForm user) {
        return userService.add(user.toRider());
    }


    @RequestMapping(method = RequestMethod.GET, value="")
    @ResponseBody
    public User getMyInfo(@CurrentUser User user) {
        return user;
    }

    @RequestMapping(method = RequestMethod.POST, value="/update")
    @ResponseBody
    public User update(@RequestBody User user) {

        return userService.update(user.getId(),user);
    }

}
