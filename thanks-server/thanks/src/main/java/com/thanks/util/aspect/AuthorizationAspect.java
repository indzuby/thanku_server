package com.thanks.util.aspect;

import com.thanks.util.AssertUtil;
import com.thanks.util.HeaderUtil;
import com.thanks.util.exception.UnauthorizedException;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by micky on 2016. 7. 23..
 */
@Aspect
public class AuthorizationAspect {

    @Autowired
    private HttpServletRequest request;

    @Before("@annotation(hasBeen.util.annotation.Authorization)")
    public void before(JoinPoint joinPoint) throws Exception {
        String authorization = request.getHeader(HeaderUtil.AUTHORIZATION);
        boolean guest = HeaderUtil.AUTHORIZATION_VALUE.equals(authorization);

        AssertUtil.isTrue(guest, new UnauthorizedException());
    }
}
