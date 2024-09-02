package com.github.ui.test.exception;

public class IllegalContextException extends RuntimeException {

    public IllegalContextException(String expectation, String actual) {
        super("Illegal context, expected was " + expectation + " but got " + actual);
    }

}
