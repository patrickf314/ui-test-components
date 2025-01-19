package com.github.ui.test.core.assertion;

import com.github.ui.test.core.component.*;

public interface UiTestAssertFactory {

    <T extends UiTestComponent> UiTestComponentAssert<T> componentAssert(T actual);

    <T extends UiTestComponent> UiTestListComponentAssert<T> listComponentAssert(UiTestComponentList<T> actual);

    HtmlButtonComponentAssert htmlButtonComponentAssert(HtmlButtonComponent actual);

    HtmlCheckboxComponentAssert htmlCheckboxComponentAssert(HtmlCheckboxComponent actual);

    <T extends UiTestComponent, D extends UiTestComponent> HtmlDefinitionListComponentAssert<T, D> htmlDefinitionListComponentAssert(HtmlDefinitionListComponent<T, D> actual);

    HtmlInputComponentAssert htmlInputComponentAssert(HtmlInputComponent actual);

    HtmlSelectComponentAssert htmlSelectComponentAssert(HtmlSelectComponent actual);

}
