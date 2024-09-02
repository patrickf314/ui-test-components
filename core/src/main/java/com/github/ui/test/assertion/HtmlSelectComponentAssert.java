package com.github.ui.test.assertion;

import com.github.ui.test.component.HtmlSelectComponent;

public interface HtmlSelectComponentAssert<SELF extends HtmlSelectComponentAssert<SELF, ACTUAL>, ACTUAL extends HtmlSelectComponent>
        extends UiTestComponentAssert<SELF, ACTUAL> {

    HtmlSelectComponentAssert<SELF, ACTUAL> hasOptions(String... options);

}
