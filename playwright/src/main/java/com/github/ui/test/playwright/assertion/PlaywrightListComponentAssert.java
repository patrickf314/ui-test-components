package com.github.ui.test.playwright.assertion;

import com.github.ui.test.core.assertion.UiTestListComponentAssert;
import com.github.ui.test.core.component.UiTestComponent;
import com.github.ui.test.core.component.UiTestComponentList;
import com.github.ui.test.playwright.context.PlaywrightComponentContext;
import com.github.ui.test.core.predicate.UiTestComponentPredicate;
import com.github.ui.test.playwright.predicate.PlaywrightComponentHasTextPredicate;
import lombok.extern.slf4j.Slf4j;
import org.opentest4j.AssertionFailedError;

import java.util.List;

import static com.github.ui.test.playwright.assertion.PlaywrightUiTestAssertions.assertThat;
import static com.github.ui.test.playwright.component.PlaywrightComponentFactory.requirePlaywrightContext;

@Slf4j
public class PlaywrightListComponentAssert<T extends UiTestComponent>
        extends AbstractPlaywrightAssert<PlaywrightListComponentAssert<T>, UiTestComponentList<T>>
        implements UiTestListComponentAssert<PlaywrightListComponentAssert<T>, T> {

    public PlaywrightListComponentAssert(UiTestComponentList<T> actual) {
        super(actual, PlaywrightListComponentAssert.class);
    }

    @Override
    protected PlaywrightComponentContext getActualContext() {
        isNotNull();
        return requirePlaywrightContext(actual.getContext());
    }

    @Override
    protected PlaywrightListComponentAssert<T> self() {
        return this;
    }

    @Override
    public PlaywrightListComponentAssert<T> hasSize(int size) {
        locatorAssertions().hasCount(size);
        return this;
    }

    @Override
    public PlaywrightListComponentAssert<T> isNotEmpty() {
        locatorAssertions().not().hasCount(0);
        return this;
    }

    @Override
    public PlaywrightListComponentAssert<T> contains(UiTestComponentPredicate predicate) {
        if (predicate instanceof PlaywrightComponentHasTextPredicate hasTextPredicate) {
            locatorAssertions().containsText(new String[]{hasTextPredicate.getText()});
            return this;
        }

        try {
            filteredListAssert(predicate).isNotEmpty();
        } catch (AssertionFailedError e) {
            failWithActualExpectedAndMessage(
                    predicate.describeActual(getActualContext()),
                    predicate.describeExpected(),
                    e.getMessage()
            );
        }
        return this;
    }

    @Override
    public PlaywrightListComponentAssert<T> containsOnly(UiTestComponentPredicate predicate) {
        filteredListAssert(predicate.negate()).isEmpty();
        return this;
    }

    @Override
    public PlaywrightListComponentAssert<T> containsExactly(List<UiTestComponentPredicate> itemPredicates) {
        hasSize(itemPredicates.size());
        for (var i = 0; i < itemPredicates.size(); i++) {
            assertThat(actual.get(i)).satisfies(itemPredicates.get(i));
        }
        return this;
    }

    private PlaywrightListComponentAssert<T> filteredListAssert(UiTestComponentPredicate predicate) {
        isNotNull();
        return new PlaywrightListComponentAssert<>(actual.filter(predicate));
    }
}