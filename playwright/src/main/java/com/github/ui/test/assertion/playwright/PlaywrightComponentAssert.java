package com.github.ui.test.assertion.playwright;

import com.github.ui.test.component.UiTestComponent;

public class PlaywrightComponentAssert<T extends UiTestComponent> extends AbstractPlaywrightComponentAssert<PlaywrightComponentAssert<T>, T> {

    public PlaywrightComponentAssert(T actual) {
        super(actual, PlaywrightComponentAssert.class);
    }

    @Override
    protected PlaywrightComponentAssert<T> self() {
        return this;
    }
}
