package com.github.ui.test.core.exception;

public class IllegalContextException extends RuntimeException {

    public IllegalContextException(String expectation, String actual) {
        super("Illegal context, expected was " + expectation + " but got " + actual);
    }

}
