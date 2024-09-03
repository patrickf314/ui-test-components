package com.github.ui.test.playwright.assertion;

import com.github.ui.test.core.component.UiTestComponent;

public class PlaywrightComponentAssert<T extends UiTestComponent> extends AbstractPlaywrightComponentAssert<PlaywrightComponentAssert<T>, T> {

    public PlaywrightComponentAssert(T actual) {
        super(actual, PlaywrightComponentAssert.class);
    }

    @Override
    protected PlaywrightComponentAssert<T> self() {
        return this;
    }
}
