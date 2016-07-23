package com.thanks.util;

import com.thanks.model.BaseModel;
import com.thanks.model.User;
import com.thanks.model.UserDetailsImpl;
import com.thanks.service.UserService;
import com.thanks.util.exception.InternalServerErrorException;
import com.thanks.util.exception.UnauthorizedException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PathVariable;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.List;
import java.util.MissingFormatArgumentException;

/**
 * Created by micky on 2016. 7. 18..
 */
public class AspectUtil {

    private static final int MIN_BOUND = -1;
    private static final String CONTROLLER = "Controller";
    private static final String DOMAIN_PACKAGE = "com.thanks.model.";
    private static Log logger = LogFactory.getLog(AspectUtil.class);

    public static int findParameterIndexByAnnotationType(JoinPoint joinPoint, Class<?> clazz) {
        Annotation[][] parameterAnnotations = ((MethodSignature) joinPoint.getSignature()).getMethod()
                .getParameterAnnotations();
        int index = MIN_BOUND;

        for (int i = 0; i < parameterAnnotations.length; i++) {
            Annotation[] annotations = parameterAnnotations[i];
            for (Annotation annotation : annotations) {
                if (annotation.annotationType() == clazz) {
                    index = i;
                    break;
                }
            }
        }

        AssertUtil.isTrue(MIN_BOUND < index,
                new InternalServerErrorException("BoundErrorOnFindParameterIndexByAnnotationType"));
        return index;
    }

    public static Annotation findParameterAnnotationByAnnotationType(JoinPoint joinPoint, Class<?> clazz) {
        Annotation[][] parameterAnnotations = ((MethodSignature) joinPoint.getSignature()).getMethod()
                .getParameterAnnotations();
        for (int i = 0; i < parameterAnnotations.length; i++) {
            Annotation[] annotations = parameterAnnotations[i];
            for (Annotation annotation : annotations) {
                if (annotation.annotationType() == clazz) {
                    return annotation;
                }
            }
        }
        AssertUtil.isNull(null, new MissingFormatArgumentException("missing argument"));
        return null;
    }

    public static boolean isPathVariable(JoinPoint joinPoint, Class<PathVariable> clazz, int parameterIndex) {
        Annotation[][] parameterAnnotations = ((MethodSignature) joinPoint.getSignature()).getMethod()
                .getParameterAnnotations();
        boolean result = false;

        Annotation[] annotations = parameterAnnotations[parameterIndex];
        for (Annotation annotation : annotations) {
            if (annotation.annotationType() == clazz) {
                result = true;
                break;
            }
        }

        return result;
    }

    @SuppressWarnings("rawtypes")
    public static boolean isResourceOwner(JoinPoint joinPoint, UserService userService) throws Exception {
        // Resource Name
        String resourceName = StringUtil.substringBefore(joinPoint.getTarget().getClass().getSimpleName(), CONTROLLER);
        logger.info("ResourceName: " + resourceName);

        // Resource by @PathVariable
        Object[] args = joinPoint.getArgs();
        Class<?> clazz = Class.forName(DOMAIN_PACKAGE + resourceName);
        BaseModel pathVariable = null;
        int parameterIndex = 0;

        for (Object object : args) {
            if (clazz.isInstance(object) && AspectUtil.isPathVariable(joinPoint, PathVariable.class, parameterIndex)) {
                pathVariable = (BaseModel) object;
                break;
            }
            parameterIndex++;
        }
        logger.info("PathVariable: " + pathVariable);

        // Resource ownership
        return ResourceUtil.isResourceOwner(userFromAccessToken(userService), pathVariable);
    }

    public static User userFromAccessToken(UserService userService) {
        // User from accessToken
        String userId = ((UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal())
                .getUsername();
        User user = userService.findById(userId);
        AssertUtil.notNull(user, new UnauthorizedException("UserDoesNotExistOnUserFromAccessTokenWithId: " + userId));
        logger.info("UserFromToken: " + user.getId());

        return user;
    }

    public static void notNullAllPathVariable(JoinPoint joinPoint) {
        List<Integer> pathVariableIndexList = new ArrayList<>();

        Annotation[][] parameterAnnotations = ((MethodSignature) joinPoint.getSignature()).getMethod()
                .getParameterAnnotations();

        for (int i = 0; i < parameterAnnotations.length; i++) {
            Annotation[] annotations = parameterAnnotations[i];
            for (Annotation annotation : annotations) {
                if (annotation.annotationType() == PathVariable.class) {
                    pathVariableIndexList.add(i);
                }
            }
        }

        for (Integer index : pathVariableIndexList) {
            AssertUtil.notNull(joinPoint.getArgs()[index], "PathVariableIsNull");
        }
    }
}
