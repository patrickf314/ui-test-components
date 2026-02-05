package com.github.ui.test.playwright.assertion;

import com.github.ui.test.core.assertion.HtmlCheckboxComponentAssert;
import com.github.ui.test.core.component.HtmlCheckboxComponent;
import com.microsoft.playwright.assertions.LocatorAssertions;

public class PlaywrightCheckboxComponentAssert
        extends AbstractPlaywrightComponentAssert<PlaywrightCheckboxComponentAssert, HtmlCheckboxComponent>
        implements HtmlCheckboxComponentAssert {

    public PlaywrightCheckboxComponentAssert(HtmlCheckboxComponent actual) {
        super(actual, PlaywrightCheckboxComponentAssert.class);
    }

    @Override
    public HtmlCheckboxComponentAssert isChecked() {
        wrapPlaywrightAssertion(LocatorAssertions::isChecked, "to be checked");
        return this;
    }

    @Override
    public HtmlCheckboxComponentAssert isNotChecked() {
        wrapPlaywrightAssertion(() -> locatorAssertions().isChecked(new LocatorAssertions.IsCheckedOptions().setChecked(false)), "to not be checked");
        return this;
    }
}
