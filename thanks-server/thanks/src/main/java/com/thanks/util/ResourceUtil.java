package com.thanks.util;

import com.thanks.model.BaseModel;
import com.thanks.model.User;
import com.thanks.util.exception.UnauthorizedException;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.IOUtils;

import java.io.IOException;

/**
 * Created by micky on 2016. 7. 18..
 */
public class ResourceUtil {

    public static boolean isResourceOwner(User currentUser, BaseModel pathVariable) {
        AssertUtil.notNull(currentUser, new UnauthorizedException("CurrentUserIsNull"));
        AssertUtil.notNull(pathVariable, new UnauthorizedException("PathVariableIsNull"));
        return true;
    }

    public static String imageToBase64EncodedString(Class clazz, String path) {
        try {
            ClassLoader classLoader = clazz.getClassLoader();
            return Base64.encodeBase64String(IOUtils.toByteArray(classLoader.getResourceAsStream(path))).toString();
        } catch (IOException e) {
            e.printStackTrace();
            throw new IllegalArgumentException("WrongClassAndPath: " + clazz + ", " + path);
        }
    }
}
