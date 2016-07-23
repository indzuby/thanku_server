package com.thanks.util.aspect;

import com.thanks.util.AccessTokenUtil;
import com.thanks.util.AspectUtil;
import com.thanks.util.AssertUtil;
import com.thanks.util.annotation.AccessToken;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.common.exceptions.UnauthorizedUserException;
import org.springframework.security.oauth2.common.exceptions.UnsupportedGrantTypeException;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.stereotype.Component;

/**
 * Created by micky on 2016. 7. 18..
 */
@Aspect
@Component
public class AccessTokenAspect {

    @Around("execution(* com.thanks.controller.*.*(.., @com.thanks.util.annotation.AccessToken (*), ..))")
    public Object setAccessTokenToParam(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {

        Object[] args = proceedingJoinPoint.getArgs();
        String accessToken = AccessTokenUtil.getAccessToken();
        int accessTokenIndex = AspectUtil.findParameterIndexByAnnotationType(proceedingJoinPoint, AccessToken.class);
        args[accessTokenIndex] = accessToken;

        return proceedingJoinPoint.proceed(args);
    }

    @Around("execution(* com.thanks.controller.*.*(.., @com.thanks.util.annotation.CheckAccessToken (*), ..))")
    public Object checkAccessTokenParam(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String accessToken = ((OAuth2AuthenticationDetails)authentication.getDetails()).getTokenValue();
        AssertUtil.isNull(accessToken, new UnauthorizedUserException("Not authorized access"));

        return proceedingJoinPoint.proceed();
    }
}
