package com.thanks.service;

import com.thanks.model.User;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * Created by micky on 2016. 7. 17..
 */
public interface UserService extends ServiceBase<User> {

    User findById(String userId);

    User findByEmail(String username);

}
