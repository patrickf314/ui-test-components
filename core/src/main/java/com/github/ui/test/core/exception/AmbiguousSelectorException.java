package com.github.ui.test.core.exception;

import com.github.ui.test.core.selector.Selector;

public class AmbiguousSelectorException extends RuntimeException {

    public AmbiguousSelectorException(Selector selector) {
        super("Selector " + selector + " selects multiple components, use selectAll to select a list.");
    }
}
