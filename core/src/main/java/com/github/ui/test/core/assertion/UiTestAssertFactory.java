package com.github.ui.test.core.assertion;

import com.github.ui.test.core.component.*;

public interface UiTestAssertFactory {

    <T extends UiTestComponent> UiTestComponentAssert<T> componentAssert(T actual);

    <T extends UiTestComponent> UiTestListComponentAssert<T> listComponentAssert(UiTestComponentList<T> actual);

    <T extends HtmlInputComponent> HtmlInputComponentAssert<T> htmlInputComponentAssert(T actual);

    <T extends HtmlSelectComponent> HtmlSelectComponentAssert<T> htmlSelectComponentAssert(T actual);

    <T extends UiTestComponent, D extends UiTestComponent> HtmlDefinitionListComponentAssert<T, D> htmlDefinitionListComponentAssert(DefinitionListComponent<T, D> actual);

}
