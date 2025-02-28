package com.github.ui.test.core.assertion;

import com.github.ui.test.core.component.*;

/**
 * A factory for construction component asserts of
 * the specific implementation
 *
 * @see com.github.ui.test.core.UiTestEnvironment#getComponentFactory()
 */
public interface UiTestAssertFactory {

    /**
     * Factory method for the basic {@link UiTestComponent} assert.
     *
     * @param actual the actual component
     * @return the component assert
     */
    UiTestComponentAssert componentAssert(UiTestComponent actual);

    <T extends UiTestComponent> UiTestListComponentAssert<T> listComponentAssert(UiTestComponentList<T> actual);

    HtmlButtonComponentAssert htmlButtonComponentAssert(HtmlButtonComponent actual);

    HtmlCheckboxComponentAssert htmlCheckboxComponentAssert(HtmlCheckboxComponent actual);

    <T extends UiTestComponent, D extends UiTestComponent> HtmlDefinitionListComponentAssert<T, D> htmlDefinitionListComponentAssert(HtmlDefinitionListComponent<T, D> actual);

    HtmlInputComponentAssert htmlInputComponentAssert(HtmlInputComponent actual);

    HtmlSelectComponentAssert htmlSelectComponentAssert(HtmlSelectComponent actual);

}
