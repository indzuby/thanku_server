package com.thanks.util;

import com.thanks.configure.OAuth2ServerConfiguration;
import com.thanks.model.User;
import org.springframework.security.crypto.codec.Base64;

public class HeaderUtil {

    public static final String AUTHORIZATION = "Authorization";
    public static final String USER_AGENT = "User-Agent";

    private static final String AUTHORIZATION_RAW =
            OAuth2ServerConfiguration.CLIENT_ID+":"+OAuth2ServerConfiguration.CLIENT_SECRET;
    public static final String AUTHORIZATION_VALUE =
            String.format("Basic %s",new String(Base64.encode(AUTHORIZATION_RAW.getBytes())));

    public static final String WEB_USER_AGENT_VALUE = "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_10_2) " +
            "AppleWebKit/537.36 (KHTML, like Gecko) Chrome/40.0.2214.111 Safari/537.36";
    public static final String ANDROID_USER_AGENT_VALUE = "Android";
    public static final String IPHONE_USER_AGENT_VALUE = "iPhone";
    public static final String ANDROID_USER_AGENT = "User-Agent=" + ANDROID_USER_AGENT_VALUE;
    public static final String IPHONE_USER_AGENT = "User-Agent=" + IPHONE_USER_AGENT_VALUE;

    public static final String NO_ANDROID_USER_AGENT = "User-Agent!=" + ANDROID_USER_AGENT_VALUE;
    public static final String NO_IPHONE_USER_AGENT = "User-Agent!=" + IPHONE_USER_AGENT_VALUE;

    public static final String WEB_BROWSER = "Mozilla/5.0 (";
    public static final String CHROME = " Chrome/";
    public static final String SAFARI = " Safari/";
    public static final String FIREFOX = " Firefox/";
    public static final String MSIE = " MSIE ";
    public static final String WOW64 = "WOW64;";

    public static boolean isMobile(String userAgent) {
        boolean isAndroid = userAgent.equals(ANDROID_USER_AGENT_VALUE);
        boolean isIphone = userAgent.equals(IPHONE_USER_AGENT_VALUE);
        boolean isWebBrowser = userAgent.startsWith(WEB_BROWSER);

        return (isAndroid || isIphone) && !isWebBrowser;
    }

    public static boolean isWebBrowser(String userAgent) {
        boolean isAndroid = userAgent.equals(ANDROID_USER_AGENT_VALUE);
        boolean isIphone = userAgent.equals(IPHONE_USER_AGENT_VALUE);
        boolean isWebBrowser = userAgent.startsWith(WEB_BROWSER);

        return isWebBrowser && !isAndroid && !isIphone;
    }

    public enum UserAgentType {
        WEB, MOBILE
    }
}
