package com.github.ui.test.playwright.assertion;

import com.github.ui.test.core.assertion.HtmlButtonComponentAssert;
import com.github.ui.test.core.component.HtmlButtonComponent;

public class PlaywrightButtonComponentAssert
        extends AbstractPlaywrightComponentAssert<PlaywrightButtonComponentAssert, HtmlButtonComponent>
        implements HtmlButtonComponentAssert {

    public PlaywrightButtonComponentAssert(HtmlButtonComponent actual) {
        super(actual, PlaywrightButtonComponentAssert.class);
    }

    @Override
    public PlaywrightButtonComponentAssert isEnabled() {
        locatorAssertions().isEnabled();
        return this;
    }

    @Override
    public PlaywrightButtonComponentAssert isDisabled() {
        locatorAssertions().isDisabled();
        return this;
    }
}
