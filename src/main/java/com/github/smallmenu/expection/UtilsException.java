package com.github.smallmenu.expection;


/**
 * UtilsException
 *
 * @author smallmenu
 */
public class UtilsException extends RuntimeException {
    private static final long serialVersionUID = 2253489756088246525L;

    public UtilsException(Throwable e) {
        super(e);
    }

    public UtilsException(String message) {
        super(message);
    }
}
