package com.github.ui.test.core.assertion;

import com.github.ui.test.core.component.HtmlInputComponent;

public interface HtmlInputComponentAssert<T extends HtmlInputComponent> extends UiTestComponentAssert<T> {

    HtmlInputComponentAssert<T> hasValue(String value);

}
