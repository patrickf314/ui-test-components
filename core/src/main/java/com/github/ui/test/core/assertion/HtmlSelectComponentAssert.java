package com.github.ui.test.core.assertion;

import com.github.ui.test.core.component.HtmlSelectComponent;

public interface HtmlSelectComponentAssert<SELF extends HtmlSelectComponentAssert<SELF, ACTUAL>, ACTUAL extends HtmlSelectComponent>
        extends UiTestComponentAssert<SELF, ACTUAL> {

    HtmlSelectComponentAssert<SELF, ACTUAL> hasOptions(String... options);

}
