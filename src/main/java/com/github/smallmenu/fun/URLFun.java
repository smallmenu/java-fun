package com.github.smallmenu.fun;

/**
 * UrlFun
 *
 * @author smallmenu
 */
public class URLFun {
    public static final String HTTP_PROTOCOL = "http";

    public static final String HTTPS_PROTOCOL = "https";

    public static final String NO_PROTOCOL = "//";

    public static final String PROTOCOL_BREAK = StringFun.COLON + NO_PROTOCOL;

    private URLFun() {
        throw new AssertionError();
    }
}
