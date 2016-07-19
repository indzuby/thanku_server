package com.thanks.service.impl;

import com.thanks.model.User;
import com.thanks.model.UserDetailsImpl;
import com.thanks.service.UserService;
import com.thanks.util.AssertUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Created by micky on 2016. 7. 17..
 */
@Slf4j
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User u = userService.findByEmail(username);
        log.info("username : {}", username);
        AssertUtil.notNull(u, "User "+username+" doesn't exist");
        return new UserDetailsImpl(u);
    }
}
