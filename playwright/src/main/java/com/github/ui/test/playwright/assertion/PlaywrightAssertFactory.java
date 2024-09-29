package com.github.ui.test.playwright.assertion;

import com.github.ui.test.core.assertion.HtmlInputComponentAssert;
import com.github.ui.test.core.assertion.UiTestAssertFactory;
import com.github.ui.test.core.component.*;

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
    public <T extends HtmlInputComponent> HtmlInputComponentAssert<T> htmlInputComponentAssert(T actual) {
        return new PlaywrightInputComponentAssert<>(actual);
    }

    @Override
    public <T extends HtmlSelectComponent> PlaywrightSelectComponentAssert<T> htmlSelectComponentAssert(T actual) {
        return new PlaywrightSelectComponentAssert<>(actual);
    }

    @Override
    public <T extends UiTestComponent, D extends UiTestComponent> PlaywrightDefinitionListComponentAssert<T, D> htmlDefinitionListComponentAssert(DefinitionListComponent<T, D> actual) {
        return new PlaywrightDefinitionListComponentAssert<>(actual);
    }
}
