package com.github.ui.test.core.assertion;

import com.github.ui.test.core.component.UiTestComponent;
import com.github.ui.test.core.predicate.UiTestComponentPredicate;

public interface UiTestComponentAssert<T extends UiTestComponent> {

    UiTestComponentAssert<T> isVisible();

    UiTestComponentAssert<T> isNotVisible();

    UiTestComponentAssert<T> hasText(String text);

    UiTestComponentAssert<T> doesNotHaveText(String text);

    UiTestComponentAssert<T> hasAttribute(String name, String value);

    UiTestComponentAssert<T> satisfies(UiTestComponentPredicate predicate);
}
