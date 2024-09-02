package com.github.ui.test.assertion;

import com.github.ui.test.component.UiTestComponent;
import com.github.ui.test.predicate.UiTestComponentPredicate;
import org.assertj.core.api.Assert;

public interface UiTestComponentAssert<SELF extends UiTestComponentAssert<SELF, ACTUAL>, ACTUAL extends UiTestComponent> extends Assert<SELF, ACTUAL> {

    SELF isVisible();

    SELF isNotVisible();

    SELF hasText(String text);

    SELF doesNotHaveText(String text);

    SELF hasAttribute(String name, String value);

    SELF satisfies(UiTestComponentPredicate predicate);
}
