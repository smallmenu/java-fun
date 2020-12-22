package com.github.smallmenu.expection;


/**
 * UtilsException
 *
 * @author smallmenu
 */
public class UtilsException extends RuntimeException {
    private static final long serialVersionUID = 2253489756088246525L;

    public UtilsException() {
        super();
    }

    public UtilsException(final Throwable e) {
        super(e);
    }

    public UtilsException(final String message) {
        super(message);
    }

    public UtilsException(final String message, final Throwable e) {
        super(message, e);
    }
}
