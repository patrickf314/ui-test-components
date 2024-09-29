package com.github.ui.test.core.assertion;

import com.github.ui.test.core.component.UiTestComponent;
import com.github.ui.test.core.predicate.UiTestComponentPredicate;
import org.assertj.core.api.AbstractAssert;

import static com.github.ui.test.core.assertion.UiTestAssertions.assertThat;

public class AbstractUiTestComponentAssert<SELF extends AbstractUiTestComponentAssert<SELF, ACTUAL>, ACTUAL extends UiTestComponent>
        extends AbstractAssert<SELF, ACTUAL>
        implements UiTestComponentAssert<ACTUAL> {

    protected AbstractUiTestComponentAssert(ACTUAL actual, Class<?> selfType) {
        super(actual, selfType);
    }

    @Override
    public SELF isVisible() {
        assertThat(actual).isVisible();
        return myself;
    }

    @Override
    public SELF isNotVisible() {
        assertThat(actual).isNotVisible();
        return myself;
    }

    @Override
    public SELF hasText(String text) {
        assertThat(actual).hasText(text);
        return myself;
    }

    @Override
    public SELF doesNotHaveText(String text) {
        assertThat(actual).doesNotHaveText(text);
        return myself;
    }

    @Override
    public SELF hasAttribute(String name, String value) {
        assertThat(actual).hasAttribute(name, value);
        return myself;
    }

    @Override
    public SELF satisfies(UiTestComponentPredicate predicate) {
        assertThat(actual).satisfies(predicate);
        return myself;
    }
}

