package com.github.ui.test.playwright.assertion;

import com.github.ui.test.core.assertion.HtmlInputComponentAssert;
import com.github.ui.test.core.component.HtmlInputComponent;

public class PlaywrightInputComponentAssert
        extends AbstractPlaywrightComponentAssert<PlaywrightInputComponentAssert, HtmlInputComponent>
        implements HtmlInputComponentAssert {

    public PlaywrightInputComponentAssert(HtmlInputComponent actual) {
        super(actual, PlaywrightInputComponentAssert.class);
    }

    @Override
    public PlaywrightInputComponentAssert hasValue(String value) {
        locatorAssertions().hasValue(value);
        return this;
    }
}