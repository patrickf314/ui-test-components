package com.github.ui.test.playwright.predicate;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.github.ui.test.core.context.UiTestComponentContext;
import com.github.ui.test.core.predicate.UiTestComponentPredicate;

import java.util.List;

import static com.github.ui.test.playwright.component.PlaywrightComponentFactory.requirePlaywrightContext;

public interface PlaywrightComponentPredicate extends UiTestComponentPredicate {

    default String describeActual(UiTestComponentContext actual) {
        return describeActual(requirePlaywrightContext(actual).getLocator());
    }

    String describeActual(Locator actual);

    @Override
    PlaywrightComponentPredicate negate();

    @Override
    default PlaywrightComponentPredicate and(UiTestComponentPredicate other) {
        return new PlaywrightComponentAllOfPredicate(List.of(this, PlaywrightComponentPredicates.requirePlaywrightPredicate(other)));
    }

    @Override
    default PlaywrightComponentPredicate or(UiTestComponentPredicate other) {
        return new PlaywrightComponentAnyOfPredicate(List.of(this, PlaywrightComponentPredicates.requirePlaywrightPredicate(other)));
    }

    Locator filter(Page page, Locator locator);

}
