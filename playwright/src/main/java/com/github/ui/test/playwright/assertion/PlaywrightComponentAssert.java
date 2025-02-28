package com.github.ui.test.playwright.assertion;

import com.github.ui.test.core.assertion.UiTestComponentAssert;
import com.github.ui.test.core.component.UiTestComponent;

public class PlaywrightComponentAssert extends AbstractPlaywrightComponentAssert<PlaywrightComponentAssert, UiTestComponent>
        implements UiTestComponentAssert {

    public PlaywrightComponentAssert(UiTestComponent actual) {
        super(actual, PlaywrightComponentAssert.class);
    }
}
