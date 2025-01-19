package com.github.ui.test.core.assertion;

import com.github.ui.test.core.component.HtmlButtonComponent;

/**
 * Extension of the {@link GenericUiTestComponentAssert} for {@link HtmlButtonComponent}
 */
public interface HtmlButtonComponentAssert extends GenericUiTestComponentAssert<HtmlButtonComponentAssert, HtmlButtonComponent> {

    /**
     * Assert that the button is enabled
     *
     * @return this
     */
    HtmlButtonComponentAssert isEnabled();

    /**
     * Assert that the button is disabled
     *
     * @return this
     */
    HtmlButtonComponentAssert isDisabled();

}
