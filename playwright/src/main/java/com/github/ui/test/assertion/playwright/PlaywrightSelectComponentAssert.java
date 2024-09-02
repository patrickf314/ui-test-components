package com.github.ui.test.assertion.playwright;

import com.github.ui.test.assertion.HtmlSelectComponentAssert;
import com.github.ui.test.component.HtmlSelectComponent;

public class PlaywrightSelectComponentAssert<T extends HtmlSelectComponent>
        extends AbstractPlaywrightComponentAssert<PlaywrightSelectComponentAssert<T>, T>
        implements HtmlSelectComponentAssert<PlaywrightSelectComponentAssert<T>, T> {

    public PlaywrightSelectComponentAssert(T actual) {
        super(actual, PlaywrightSelectComponentAssert.class);
    }

    @Override
    protected PlaywrightSelectComponentAssert<T> self() {
        return this;
    }

    public PlaywrightSelectComponentAssert<T> hasOptions(String... options) {
        var optionsLocator = getActualLocator().locator("option");
        locatorAssertions(optionsLocator).hasCount(options.length);
        locatorAssertions(optionsLocator).containsText(options);
        return this;
    }
}
