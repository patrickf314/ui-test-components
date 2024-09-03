package com.github.ui.test.core.predicate;

import com.github.ui.test.core.UiTestEnvironment;
import com.github.ui.test.core.context.UiTestComponentContext;

public interface UiTestComponentPredicate {

    String describeExpected();

    String describeActual(UiTestComponentContext actual);

    UiTestComponentPredicate negate();

    default UiTestComponentPredicate and(UiTestComponentPredicate other) {
        return UiTestEnvironment.allOf(this, other);
    }

    default UiTestComponentPredicate or(UiTestComponentPredicate other) {
        return UiTestEnvironment.anyOf(this, other);
    }
}
