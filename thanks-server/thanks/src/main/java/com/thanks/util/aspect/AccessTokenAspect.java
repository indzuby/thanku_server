package com.thanks.util.aspect;

import com.thanks.util.AspectUtil;
import com.thanks.util.annotation.AccessToken;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.stereotype.Component;

/**
 * Created by micky on 2016. 7. 18..
 */
@Aspect
@Component
public class AccessTokenAspect {

    @Around("execution(* com.thanks.controller.*.*(.., @com.thanks.util.annotation.AccessToken (*), ..))")
    public Object before(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        Object[] args = proceedingJoinPoint.getArgs();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String accessToken = ((OAuth2AuthenticationDetails) authentication.getDetails()).getTokenValue();
        int accessTokenIndex = AspectUtil.findParameterIndexByAnnotationType(proceedingJoinPoint, AccessToken.class);
        args[accessTokenIndex] = accessToken;

        return proceedingJoinPoint.proceed(args);
    }
}
