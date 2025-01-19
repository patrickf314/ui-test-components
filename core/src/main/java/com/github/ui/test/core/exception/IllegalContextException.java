package com.github.ui.test.core.exception;

/**
 * A {@link RuntimeException} which is thrown if a component is
 * used with an illegal context.
 */
public class IllegalContextException extends RuntimeException {

    /**
     * Constructor
     *
     * @param expectation the expected context type
     * @param actual the actual context type
     */
    public IllegalContextException(String expectation, String actual) {
        super("Illegal context, expected was " + expectation + " but got " + actual);
    }

}
