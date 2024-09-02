package com.github.ui.test.predicate;

import com.github.ui.test.context.UiTestComponentContext;

public interface UiTestComponentPredicate {

    String describeExpected();

    String describeActual(UiTestComponentContext actual);

    UiTestComponentPredicate negate();

    default UiTestComponentPredicate and(UiTestComponentPredicate other) {
        return UiTestComponentPredicates.allOf(this, other);
    }

    default UiTestComponentPredicate or(UiTestComponentPredicate other) {
        return UiTestComponentPredicates.anyOf(this, other);
    }
}
