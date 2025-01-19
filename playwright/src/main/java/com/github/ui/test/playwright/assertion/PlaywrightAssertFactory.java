package com.github.ui.test.playwright.assertion;

import com.github.ui.test.core.assertion.HtmlButtonComponentAssert;
import com.github.ui.test.core.assertion.HtmlCheckboxComponentAssert;
import com.github.ui.test.core.assertion.HtmlInputComponentAssert;
import com.github.ui.test.core.assertion.UiTestAssertFactory;
import com.github.ui.test.core.component.*;
import com.github.ui.test.playwright.component.PlaywrightCheckboxComponent;

public class PlaywrightAssertFactory implements UiTestAssertFactory {

    @Override
    public <T extends UiTestComponent> PlaywrightComponentAssert<T> componentAssert(T actual) {
        return new PlaywrightComponentAssert<>(actual);
    }

    @Override
    public <T extends UiTestComponent> PlaywrightListComponentAssert<T> listComponentAssert(UiTestComponentList<T> actual) {
        return new PlaywrightListComponentAssert<>(actual);
    }

    @Override
    public HtmlButtonComponentAssert htmlButtonComponentAssert(HtmlButtonComponent actual) {
        return new PlaywrightButtonComponentAssert(actual);
    }

    @Override
    public HtmlCheckboxComponentAssert htmlCheckboxComponentAssert(HtmlCheckboxComponent actual) {
        return new PlaywrightCheckboxComponentAssert(actual);
    }

    @Override
    public <T extends UiTestComponent, D extends UiTestComponent> PlaywrightDefinitionListComponentAssert<T, D> htmlDefinitionListComponentAssert(HtmlDefinitionListComponent<T, D> actual) {
        return new PlaywrightDefinitionListComponentAssert<>(actual);
    }

    @Override
    public HtmlInputComponentAssert htmlInputComponentAssert(HtmlInputComponent actual) {
        return new PlaywrightInputComponentAssert(actual);
    }

    @Override
    public PlaywrightSelectComponentAssert htmlSelectComponentAssert(HtmlSelectComponent actual) {
        return new PlaywrightSelectComponentAssert(actual);
    }
}
