package com.github.ui.test.core.assertion;

import com.github.ui.test.core.component.UiTestComponent;
import com.github.ui.test.core.predicate.UiTestComponentPredicate;
import org.assertj.core.api.Assert;

public interface UiTestComponentAssert<SELF extends UiTestComponentAssert<SELF, ACTUAL>, ACTUAL extends UiTestComponent> extends Assert<SELF, ACTUAL> {

    SELF isVisible();

    SELF isNotVisible();

    SELF hasText(String text);

    SELF doesNotHaveText(String text);

    SELF hasAttribute(String name, String value);

    SELF satisfies(UiTestComponentPredicate predicate);
}
