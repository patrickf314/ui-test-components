package com.github.ui.test.playwright.assertion;

import com.github.ui.test.core.assertion.HtmlInputComponentAssert;
import com.github.ui.test.core.component.HtmlInputComponent;

public class PlaywrightInputComponentAssert<T extends HtmlInputComponent>
        extends AbstractPlaywrightComponentAssert<PlaywrightInputComponentAssert<T>, T>
        implements HtmlInputComponentAssert<T> {

    public PlaywrightInputComponentAssert(T actual) {
        super(actual, PlaywrightInputComponentAssert.class);
    }

    @Override
    public PlaywrightInputComponentAssert<T> hasValue(String value) {
        locatorAssertions().hasValue(value);
        return this;
    }
}