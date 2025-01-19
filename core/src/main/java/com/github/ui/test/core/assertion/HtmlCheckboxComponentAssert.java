package com.github.ui.test.core.assertion;

import com.github.ui.test.core.component.HtmlCheckboxComponent;

/**
 * Extension of the {@link GenericUiTestComponentAssert} for {@link HtmlCheckboxComponent}
 */
public interface HtmlCheckboxComponentAssert extends GenericUiTestComponentAssert<HtmlCheckboxComponentAssert, HtmlCheckboxComponent> {

    /**
     * Assert that the checkbox is checked
     *
     * @return this
     */
    HtmlCheckboxComponentAssert isChecked();

    /**
     * Assert that the checkbox is not checked
     *
     * @return this
     */
    HtmlCheckboxComponentAssert isNotChecked();

}
