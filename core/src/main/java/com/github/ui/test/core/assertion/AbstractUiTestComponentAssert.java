package com.github.ui.test.core.assertion;

import com.github.ui.test.core.component.UiTestComponent;
import com.github.ui.test.core.predicate.UiTestComponentPredicate;
import org.assertj.core.api.AbstractAssert;

import static com.github.ui.test.core.assertion.UiTestAssertions.assertThat;

/**
 * Abstract implementation of a {@link GenericUiTestComponentAssert} which can be used for custom implementation of
 * {@link GenericUiTestComponentAssert}s. This class used the default Assert returned by
 * {@link UiTestAssertions#assertThat(UiTestComponent)} to perform the required assertions of the {@link GenericUiTestComponentAssert}
 *
 * @param <SELF>   the type of the assert class
 * @param <ACTUAL> the type of the {@link UiTestComponent}, which this assert asserts.
 */
public class AbstractUiTestComponentAssert<SELF extends AbstractUiTestComponentAssert<SELF, ACTUAL>, ACTUAL extends UiTestComponent>
        extends AbstractAssert<SELF, ACTUAL>
        implements GenericUiTestComponentAssert<SELF, ACTUAL> {

    /**
     * Constructor
     *
     * @param actual the actual component
     * @param selfType the type of the assert implementation
     */
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
    public SELF hasClass(String className) {
        assertThat(actual).hasClass(className);
        return null;
    }

    @Override
    public SELF satisfies(UiTestComponentPredicate predicate) {
        assertThat(actual).satisfies(predicate);
        return myself;
    }

    @Override
    public SELF doesNotSatisfy(UiTestComponentPredicate predicate) {
        assertThat(actual).doesNotSatisfy(predicate);
        return myself;
    }
}

