package com.thanks.controller;

import com.thanks.form.EmailSignUpForm;
import com.thanks.model.Inquire;
import com.thanks.model.User;
import com.thanks.service.InquireService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by zuby on 2016. 7. 23..
 */
@Controller
@RequestMapping("/api/inquire")
public class InquireController {

    @Autowired
    InquireService inquireService;

    @RequestMapping(method= RequestMethod.POST, value="")
    @ResponseBody
    public Inquire inquire(@RequestBody Inquire inquire) {
        return inquireService.add(inquire);
    }
}
