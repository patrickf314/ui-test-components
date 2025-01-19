package com.github.ui.test.core.assertion;

import com.github.ui.test.core.component.HtmlInputComponent;

/**
 * Extension of the {@link GenericUiTestComponentAssert} for {@link HtmlInputComponent}
 */
public interface HtmlInputComponentAssert extends GenericUiTestComponentAssert<HtmlInputComponentAssert, HtmlInputComponent> {

    /**
     * Assert that the input field has the given value
     *
     * @param value the expected value
     * @return this
     */
    HtmlInputComponentAssert hasValue(String value);

}
