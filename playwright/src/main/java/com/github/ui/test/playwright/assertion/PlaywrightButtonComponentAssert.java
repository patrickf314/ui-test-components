package com.github.ui.test.playwright.assertion;

import com.github.ui.test.core.assertion.HtmlButtonComponentAssert;
import com.github.ui.test.core.component.HtmlButtonComponent;
import com.microsoft.playwright.assertions.LocatorAssertions;

public class PlaywrightButtonComponentAssert
        extends AbstractPlaywrightComponentAssert<PlaywrightButtonComponentAssert, HtmlButtonComponent>
        implements HtmlButtonComponentAssert {

    public PlaywrightButtonComponentAssert(HtmlButtonComponent actual) {
        super(actual, PlaywrightButtonComponentAssert.class);
    }

    @Override
    public PlaywrightButtonComponentAssert isEnabled() {
        wrapPlaywrightAssertion(LocatorAssertions::isEnabled, "to be enabled");
        return this;
    }

    @Override
    public PlaywrightButtonComponentAssert isDisabled() {
        wrapPlaywrightAssertion(LocatorAssertions::isDisabled, "to be disabled");
        return this;
    }
}
