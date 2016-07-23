package com.thanks.util.aspect;

import com.thanks.model.User;
import com.thanks.service.UserService;
import com.thanks.util.AccessTokenUtil;
import com.thanks.util.AspectUtil;
import com.thanks.util.annotation.AccessToken;
import com.thanks.util.annotation.CurrentUser;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;

/**
 * Created by micky on 2016. 7. 23..
 */
@Aspect
@Component
@Slf4j
public class UserAspect {

    @Autowired
    private UserService userService;


    @Around("execution(* com.thanks.controller.*.*(.., @com.thanks.util.annotation.CurrentUser (*), ..))")
    public Object setCurrentUser(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        Object[] args = proceedingJoinPoint.getArgs();
        Authentication authentication = AccessTokenUtil.getAuthentication();
        log.debug("user {}", authentication);
        User user = (null == authentication)?null:userService.findById(authentication.getName());
        int userIndex = AspectUtil.findParameterIndexByAnnotationType(proceedingJoinPoint, CurrentUser.class);

        args[userIndex] = user;

        return proceedingJoinPoint.proceed(args);
    }

}
