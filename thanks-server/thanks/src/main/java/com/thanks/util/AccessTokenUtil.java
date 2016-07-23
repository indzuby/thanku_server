package com.thanks.util;

import com.thanks.util.annotation.AccessToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;

/**
 * Created by micky on 2016. 7. 23..
 */
public class AccessTokenUtil {

    public static Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    public static String getAccessToken() {
        return ((OAuth2AuthenticationDetails)getAuthentication().getDetails()).getTokenValue();
    }

}
