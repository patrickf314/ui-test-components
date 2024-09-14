package com.github.ui.test.playwright.assertion;

import com.github.ui.test.playwright.predicate.PlaywrightComponentPredicate;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.assertions.LocatorAssertions;
import com.microsoft.playwright.assertions.PlaywrightAssertions;
import com.github.ui.test.playwright.context.PlaywrightComponentContext;
import org.assertj.core.api.AbstractAssert;

public abstract class AbstractPlaywrightAssert<SELF extends AbstractPlaywrightAssert<SELF, ACTUAl>, ACTUAl>
        extends AbstractAssert<SELF, ACTUAl> {

    protected AbstractPlaywrightAssert(ACTUAl actual, Class<?> selfType) {
        super(actual, selfType);
    }

    protected abstract PlaywrightComponentContext getActualContext();

    protected Locator getActualLocator() {
        return getActualContext().getLocator();
    }

    protected Page getActualPage() {
        return getActualContext().getPageContext().getPage();
    }

    protected LocatorAssertions locatorAssertions() {
        return locatorAssertions(getActualLocator());
    }

    protected LocatorAssertions locatorAssertions(Locator locator) {
        return PlaywrightAssertions.assertThat(locator);
    }

    protected AssertionError withDescribedPredicate(AssertionError error, PlaywrightComponentPredicate predicate) {
        return withActualExpected(error, predicate.describeActual(getActualLocator()), predicate.describeExpected());
    }

    protected AssertionError withActualExpected(AssertionError error, String actual, String expected) {
        return failureWithActualExpected(
                actual,
                expected,
                error.getMessage()
        );
    }
}
