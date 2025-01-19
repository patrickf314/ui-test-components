package com.github.ui.test.playwright.assertion;

import com.github.ui.test.core.assertion.HtmlSelectComponentAssert;
import com.github.ui.test.core.component.HtmlSelectComponent;
import com.github.ui.test.core.component.UiTestComponent;
import com.github.ui.test.core.predicate.UiTestComponentPredicate;
import org.opentest4j.AssertionFailedError;

public class PlaywrightSelectComponentAssert
        extends AbstractPlaywrightComponentAssert<PlaywrightSelectComponentAssert, HtmlSelectComponent>
        implements HtmlSelectComponentAssert {

    public PlaywrightSelectComponentAssert(HtmlSelectComponent actual) {
        super(actual, PlaywrightSelectComponentAssert.class);
    }

    public PlaywrightSelectComponentAssert containsOptions(String... options) {
        optionsAssert().containsExactlyElementsWithText(options);
        return this;
    }

    @Override
    public HtmlSelectComponentAssert containsOption(UiTestComponentPredicate predicate) {
        try {
            optionsAssert().contains(predicate);
        } catch (AssertionFailedError e) {
            throw withDescribedPredicate(e, predicate);
        }
        return this;
    }


    private PlaywrightListComponentAssert<UiTestComponent> optionsAssert() {
        return new PlaywrightListComponentAssert<>(actual.getOptions());
    }
}
