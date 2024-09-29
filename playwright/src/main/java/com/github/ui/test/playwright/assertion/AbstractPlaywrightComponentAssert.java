package com.github.ui.test.playwright.assertion;

import com.github.ui.test.core.assertion.UiTestComponentAssert;
import com.github.ui.test.core.component.UiTestComponent;
import com.github.ui.test.core.predicate.UiTestComponentPredicate;
import com.github.ui.test.playwright.component.PlaywrightComponentFactory;
import com.github.ui.test.playwright.context.PlaywrightComponentContext;
import com.github.ui.test.playwright.predicate.PlaywrightComponentHasTextPredicate;
import com.github.ui.test.playwright.predicate.PlaywrightComponentPredicateFactory;
import com.github.ui.test.playwright.predicate.PlaywrightComponentVisiblePredicate;

public abstract class AbstractPlaywrightComponentAssert<SELF extends AbstractPlaywrightComponentAssert<SELF, ACTUAl>, ACTUAl extends UiTestComponent>
        extends AbstractPlaywrightAssert<SELF, ACTUAl>
        implements UiTestComponentAssert<ACTUAl> {

    protected AbstractPlaywrightComponentAssert(ACTUAl actual, Class<?> selfType) {
        super(actual, selfType);
    }

    @Override
    public SELF isVisible() {
        try {
            locatorAssertions().isVisible();
        } catch (AssertionError e) {
            throw withActualExpected(e, PlaywrightComponentVisiblePredicate.INVISIBLE, PlaywrightComponentVisiblePredicate.VISIBLE);
        }
        return myself;
    }

    @Override
    public SELF isNotVisible() {
        try {
            locatorAssertions().isHidden();
        } catch (AssertionError e) {
            throw withActualExpected(e, PlaywrightComponentVisiblePredicate.VISIBLE, PlaywrightComponentVisiblePredicate.INVISIBLE);
        }
        return myself;
    }

    @Override
    public SELF hasText(String text) {
        try {
            locatorAssertions().hasText(text);
        } catch (AssertionError e) {
            throw withActualExpected(e, PlaywrightComponentHasTextPredicate.describeText(getActualLocator().textContent()), PlaywrightComponentHasTextPredicate.describeText(text));
        }
        return myself;
    }

    @Override
    public SELF doesNotHaveText(String text) {
        try {
            locatorAssertions().not().hasText(text);
        } catch (AssertionError e) {
            throw withActualExpected(e, PlaywrightComponentHasTextPredicate.describeText(getActualLocator().textContent()), PlaywrightComponentHasTextPredicate.describeNotText(text));
        }
        return myself;
    }

    @Override
    public SELF hasAttribute(String name, String value) {
        try {
            locatorAssertions().hasAttribute(name, value);
        } catch (AssertionError e) {
            var actual = getActualLocator().getAttribute(name);
            throw withActualExpected(e,
                    "attribute('" + name + "', " + (actual == null ? "<NULL>" : "'" + actual + "'") + ")",
                    "attribute('" + name + "', '" + value + "')"
            );
        }
        return myself;
    }

    @Override
    public SELF satisfies(UiTestComponentPredicate predicate) {
        var playwrightPredicate = PlaywrightComponentPredicateFactory.requirePlaywrightPredicate(predicate);
        try {
            locatorAssertions(playwrightPredicate.filter(getActualPage(), getActualLocator())).hasCount(1);
        } catch (AssertionError e) {
            throw withDescribedPredicate(e, playwrightPredicate);
        }
        return myself;
    }

    protected PlaywrightComponentContext getActualContext() {
        isNotNull();
        return PlaywrightComponentFactory.requirePlaywrightContext(actual.getContext());
    }
}
