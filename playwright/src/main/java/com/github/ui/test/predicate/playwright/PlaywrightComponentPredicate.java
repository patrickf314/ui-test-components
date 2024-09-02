package com.github.ui.test.predicate.playwright;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.github.ui.test.context.UiTestComponentContext;
import com.github.ui.test.predicate.UiTestComponentPredicate;

import java.util.List;

import static com.github.ui.test.component.playwright.PlaywrightComponentFactory.requirePlaywrightContext;
import static com.github.ui.test.predicate.playwright.PlaywrightComponentPredicates.requirePlaywrightPredicate;

public interface PlaywrightComponentPredicate extends UiTestComponentPredicate {

    default String describeActual(UiTestComponentContext actual) {
        return describeActual(requirePlaywrightContext(actual).getLocator());
    }

    String describeActual(Locator actual);

    @Override
    PlaywrightComponentPredicate negate();

    @Override
    default PlaywrightComponentPredicate and(UiTestComponentPredicate other) {
        return new PlaywrightComponentAllOfPredicate(List.of(this, requirePlaywrightPredicate(other)));
    }

    @Override
    default PlaywrightComponentPredicate or(UiTestComponentPredicate other) {
        return new PlaywrightComponentAnyOfPredicate(List.of(this, requirePlaywrightPredicate(other)));
    }

    Locator filter(Page page, Locator locator);

}
