package com.github.ui.test.core.assertion;

import com.github.ui.test.core.component.HtmlSelectComponent;

public interface HtmlSelectComponentAssert<T extends HtmlSelectComponent> extends UiTestComponentAssert<T> {

    HtmlSelectComponentAssert<T> hasOptions(String... options);

}
