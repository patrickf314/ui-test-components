package com.github.ui.test.assertion.playwright;

import com.github.ui.test.assertion.UiTestComponentAssert;
import com.github.ui.test.component.UiTestComponent;
import com.github.ui.test.context.playwright.PlaywrightComponentContext;
import com.github.ui.test.predicate.UiTestComponentPredicate;
import com.github.ui.test.predicate.playwright.PlaywrightComponentHasTextPredicate;
import com.github.ui.test.predicate.playwright.PlaywrightComponentVisiblePredicate;

import static com.github.ui.test.component.playwright.PlaywrightComponentFactory.requirePlaywrightContext;
import static com.github.ui.test.predicate.playwright.PlaywrightComponentHasTextPredicate.describeNotText;
import static com.github.ui.test.predicate.playwright.PlaywrightComponentHasTextPredicate.describeText;
import static com.github.ui.test.predicate.playwright.PlaywrightComponentPredicates.requirePlaywrightPredicate;
import static com.github.ui.test.predicate.playwright.PlaywrightComponentVisiblePredicate.INVISIBLE;
import static com.github.ui.test.predicate.playwright.PlaywrightComponentVisiblePredicate.VISIBLE;

public abstract class AbstractPlaywrightComponentAssert<SELF extends AbstractPlaywrightComponentAssert<SELF, ACTUAl>, ACTUAl extends UiTestComponent>
        extends AbstractPlaywrightAssert<SELF, ACTUAl>
        implements UiTestComponentAssert<SELF, ACTUAl> {

    protected AbstractPlaywrightComponentAssert(ACTUAl actual, Class<?> selfType) {
        super(actual, selfType);
    }

    @Override
    public SELF isVisible() {
        try {
            locatorAssertions().isVisible();
        } catch (AssertionError e) {
            throw withActualExpected(e, INVISIBLE, VISIBLE);
        }
        return self();
    }

    @Override
    public SELF isNotVisible() {
        try {
            locatorAssertions().isHidden();
        } catch (AssertionError e) {
            throw withActualExpected(e, VISIBLE, INVISIBLE);
        }
        return self();
    }

    @Override
    public SELF hasText(String text) {
        try {
            locatorAssertions().hasText(text);
        } catch (AssertionError e) {
            throw withActualExpected(e, describeText(getActualLocator().textContent()), describeText(text));
        }
        return self();
    }

    @Override
    public SELF doesNotHaveText(String text) {
        try {
            locatorAssertions().not().hasText(text);
        } catch (AssertionError e) {
            throw withActualExpected(e, describeText(getActualLocator().textContent()), describeNotText(text));
        }
        return self();
    }

    @Override
    public SELF hasAttribute(String name, String value) {
        try {
            locatorAssertions().hasAttribute(name, value);
        } catch (AssertionError e) {
            var actual = getActualLocator().getAttribute(name);
            throw withActualExpected(e,
                    "attribute('" + name + "', " + (actual == null ? "<NULL>" : "'" + actual + "'") + ")",
                    "attribute('" + name + "', '" + value + "')");
        }
        return self();
    }

    @Override
    public SELF satisfies(UiTestComponentPredicate predicate) {
        if (predicate instanceof PlaywrightComponentHasTextPredicate hasTextPredicate) {
            return hasTextPredicate.isNot() ? doesNotHaveText(hasTextPredicate.getText()) : hasText(hasTextPredicate.getText());
        }

        if (predicate instanceof PlaywrightComponentVisiblePredicate visiblePredicate) {
            return visiblePredicate.isNot() ? isNotVisible() : isVisible();
        }

        var playwrightPredicate = requirePlaywrightPredicate(predicate);
        try {
            locatorAssertions(playwrightPredicate.filter(getActualPage(), getActualLocator())).hasCount(1);
        } catch (AssertionError e) {
            throw withDescribedPredicate(e, playwrightPredicate);
        }

        return self();
    }

    protected PlaywrightComponentContext getActualContext() {
        isNotNull();
        return requirePlaywrightContext(actual.getContext());
    }
}
