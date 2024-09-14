package com.github.ui.test.playwright.assertion;

import com.github.ui.test.core.assertion.HtmlSelectComponentAssert;
import com.github.ui.test.core.component.HtmlSelectComponent;

public class PlaywrightSelectComponentAssert<T extends HtmlSelectComponent>
        extends AbstractPlaywrightComponentAssert<PlaywrightSelectComponentAssert<T>, T>
        implements HtmlSelectComponentAssert<PlaywrightSelectComponentAssert<T>, T> {

    public PlaywrightSelectComponentAssert(T actual) {
        super(actual, PlaywrightSelectComponentAssert.class);
    }

    public PlaywrightSelectComponentAssert<T> hasOptions(String... options) {
        var optionsLocator = getActualLocator().locator("option");
        locatorAssertions(optionsLocator).hasCount(options.length);
        locatorAssertions(optionsLocator).containsText(options);
        return this;
    }
}
