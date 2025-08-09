package com.github.ui.test.playwright.assertion;

import com.github.ui.test.core.assertion.HtmlImageComponentAssert;
import com.github.ui.test.core.component.HtmlImageComponent;

public class PlaywrightImageComponentAssert extends AbstractPlaywrightComponentAssert<PlaywrightImageComponentAssert, HtmlImageComponent>
        implements HtmlImageComponentAssert {

    public PlaywrightImageComponentAssert(HtmlImageComponent htmlImageComponent) {
        super(htmlImageComponent, PlaywrightImageComponentAssert.class);
    }

    @Override
    public HtmlImageComponentAssert isLoaded() {
        var success = waitUntilImageLoaded();
        if(!success) {
            throw failure("Image was not loaded successfully.");
        }
        return this;
    }

    @Override
    public HtmlImageComponentAssert hasFailedLoading() {
        var success = waitUntilImageLoaded();
        if(success) {
            throw failure("Image was loaded successfully.");
        }
        return this;
    }

    private boolean waitUntilImageLoaded() {
        return Boolean.TRUE == getActualContext().evaluateScript("/js/waitUntilImageLoaded.js");
    }
}
