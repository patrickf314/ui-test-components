package com.github.ui.test.playwright.assertion;

import com.github.ui.test.core.component.UiTestComponent;
import com.github.ui.test.core.predicate.UiTestComponentPredicate;
import com.github.ui.test.playwright.component.PlaywrightComponentFactory;
import com.github.ui.test.playwright.context.PlaywrightComponentContext;
import com.github.ui.test.playwright.predicate.PlaywrightComponentHasTextPredicate;
import com.github.ui.test.playwright.predicate.PlaywrightComponentPredicateFactory;
import com.github.ui.test.playwright.predicate.PlaywrightComponentVisiblePredicate;

import java.util.function.Consumer;

public abstract class AbstractPlaywrightComponentAssert<SELF extends AbstractPlaywrightComponentAssert<SELF, ACTUAL>, ACTUAL extends UiTestComponent>
        extends AbstractPlaywrightAssert<SELF, ACTUAL> {

    protected AbstractPlaywrightComponentAssert(ACTUAL actual, Class<?> selfType) {
        super(actual, selfType);
    }

    public SELF isVisible() {
        try {
            locatorAssertions().isVisible();
        } catch (AssertionError e) {
            throw withActualExpected(e, PlaywrightComponentVisiblePredicate.INVISIBLE, PlaywrightComponentVisiblePredicate.VISIBLE);
        }
        return myself;
    }

    public SELF isNotVisible() {
        try {
            locatorAssertions().isHidden();
        } catch (AssertionError e) {
            throw withActualExpected(e, PlaywrightComponentVisiblePredicate.VISIBLE, PlaywrightComponentVisiblePredicate.INVISIBLE);
        }
        return myself;
    }

    public SELF hasText(String text) {
        try {
            locatorAssertions().hasText(text);
        } catch (AssertionError e) {
            throw withActualExpected(e, PlaywrightComponentHasTextPredicate.describeText(getActualLocator().textContent()), PlaywrightComponentHasTextPredicate.describeText(text));
        }
        return myself;
    }

    public SELF doesNotHaveText(String text) {
        try {
            locatorAssertions().not().hasText(text);
        } catch (AssertionError e) {
            throw withActualExpected(e, PlaywrightComponentHasTextPredicate.describeText(getActualLocator().textContent()), PlaywrightComponentHasTextPredicate.describeNotText(text));
        }
        return myself;
    }

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

    public SELF hasClass(String className) {
        try {
            locatorAssertions().hasClass(className);
        } catch (AssertionError e) {
            var actual = getActualLocator().getAttribute("class");
            throw withActualExpected(e,
                    "class(" + (actual == null ? "<NULL>" : "'" + actual + "'") + ")",
                    "class containing '" + className + "'"
            );
        }
        return myself;
    }

    public SELF satisfies(UiTestComponentPredicate predicate) {
        var playwrightPredicate = PlaywrightComponentPredicateFactory.requirePlaywrightPredicate(predicate);
        try {
            locatorAssertions(playwrightPredicate.filter(getActualPage(), getActualLocator())).hasCount(1);
        } catch (AssertionError e) {
            throw withDescribedPredicate(e, playwrightPredicate);
        }
        return myself;
    }

    public SELF doesNotSatisfy(UiTestComponentPredicate predicate) {
        var playwrightPredicate = PlaywrightComponentPredicateFactory.requirePlaywrightPredicate(predicate);
        try {
            locatorAssertions(playwrightPredicate.filter(getActualPage(), getActualLocator())).isEmpty();
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
