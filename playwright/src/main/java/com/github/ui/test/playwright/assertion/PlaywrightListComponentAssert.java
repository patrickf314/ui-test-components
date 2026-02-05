package com.github.ui.test.playwright.assertion;

import com.github.ui.test.core.assertion.UiTestComponentAssert;
import com.github.ui.test.core.assertion.UiTestListComponentAssert;
import com.github.ui.test.core.component.UiTestComponent;
import com.github.ui.test.core.component.UiTestComponentList;
import com.github.ui.test.core.predicate.UiTestComponentPredicate;
import com.github.ui.test.playwright.context.PlaywrightComponentContext;
import lombok.extern.slf4j.Slf4j;
import org.opentest4j.AssertionFailedError;

import java.util.List;
import java.util.function.Function;

import static com.github.ui.test.core.assertion.UiTestAssertions.assertThat;
import static com.github.ui.test.playwright.component.PlaywrightComponentFactory.requirePlaywrightContext;
import static com.github.ui.test.playwright.predicate.PlaywrightComponentPredicateFactory.requirePlaywrightPredicate;

@Slf4j
public class PlaywrightListComponentAssert<T extends UiTestComponent>
        extends AbstractPlaywrightAssert<PlaywrightListComponentAssert<T>, UiTestComponentList<T>>
        implements UiTestListComponentAssert<T> {

    public PlaywrightListComponentAssert(UiTestComponentList<T> actual) {
        super(actual, PlaywrightListComponentAssert.class);
    }

    @Override
    protected PlaywrightComponentContext getActualContext() {
        isNotNull();
        return requirePlaywrightContext(actual.getContext());
    }

    @Override
    public PlaywrightListComponentAssert<T> hasSize(int size) {
        try {
            locatorAssertions().hasCount(size);
        }catch (AssertionError error) {
            var actual = getActualLocator().count();
            throw withActualExpected(error, "size(" + actual + ")", "size(" + size + ")");
        }
        return this;
    }

    @Override
    public PlaywrightListComponentAssert<T> isNotEmpty() {
        wrapPlaywrightAssertion(() -> locatorAssertions().not().hasCount(0), "not to be empty");
        return this;
    }

    @Override
    public PlaywrightListComponentAssert<T> contains(UiTestComponentPredicate predicate) {
        try {
            filteredListAssert(predicate).isNotEmpty();
        } catch (AssertionFailedError e) {
            throw withDescribedPredicate(e, requirePlaywrightPredicate(predicate));
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

    @Override
    public PlaywrightListComponentAssert<T> containsExactlyInAnyOrder(List<UiTestComponentPredicate> itemPredicates) {
        hasSize(itemPredicates.size());
        for (var itemPredicate : itemPredicates) {
            filteredListAssert(itemPredicate).hasSize(1);
        }
        return this;
    }


    @Override
    public UiTestComponentAssert first() {
        return assertThat(actual.get(0));
    }

    @Override
    public <S extends UiTestComponent> UiTestListComponentAssert<S> extracting(Function<T, S> mapper) {
        isNotNull();
        return new PlaywrightListComponentAssert<>(actual.map(mapper));
    }

    private PlaywrightListComponentAssert<T> filteredListAssert(UiTestComponentPredicate predicate) {
        isNotNull();
        return new PlaywrightListComponentAssert<>(actual.filter(predicate));
    }
}
