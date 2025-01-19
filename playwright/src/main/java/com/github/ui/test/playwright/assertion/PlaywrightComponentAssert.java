package com.github.ui.test.playwright.assertion;

import com.github.ui.test.core.assertion.UiTestComponentAssert;
import com.github.ui.test.core.component.UiTestComponent;

public class PlaywrightComponentAssert<ACTUAL extends UiTestComponent> extends AbstractPlaywrightComponentAssert<PlaywrightComponentAssert<ACTUAL>, ACTUAL>
        implements UiTestComponentAssert<ACTUAL> {

    public PlaywrightComponentAssert(ACTUAL actual) {
        super(actual, PlaywrightComponentAssert.class);
    }
}
