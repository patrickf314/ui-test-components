package com.github.ui.test.core.assertion;

import com.github.ui.test.core.component.UiTestComponent;
import com.github.ui.test.core.predicate.UiTestComponentPredicate;

/**
 * The generic base interface of a {@link UiTestComponent} assert
 *
 * @param <SELF>   the type of the assert class
 * @param <ACTUAL> the type of the {@link UiTestComponent}, which this assert asserts.
 */
public interface GenericUiTestComponentAssert<SELF extends GenericUiTestComponentAssert<SELF, ACTUAL>, ACTUAL extends UiTestComponent> {

    /**
     * Asserts that the component is visible
     *
     * @return this
     */
    SELF isVisible();

    /**
     * Asserts that the component is not visible
     *
     * @return this
     */
    SELF isNotVisible();

    /**
     * Asserts that the component has the given text
     *
     * @param text the expected text
     * @return this
     */
    SELF hasText(String text);

    /**
     * Asserts that the component does not have the given text
     *
     * @param text the not-expected text
     * @return this
     */
    SELF doesNotHaveText(String text);

    /**
     * Asserts that the component has the attribute
     *
     * @param name  the attribute name
     * @param value the attribute value
     * @return this
     */
    SELF hasAttribute(String name, String value);

    /**
     * Asserts that the component has the given class
     *
     * @param className the class name
     * @return this
     */
    SELF hasClass(String className);

    /**
     * Asserts that the component matches the given predicate
     *
     * @param predicate the {@link UiTestComponentPredicate}
     * @return this
     */
    SELF satisfies(UiTestComponentPredicate predicate);

    /**
     * Asserts that the component does not match the given predicate
     *
     * @param predicate the {@link UiTestComponentPredicate}
     * @return this
     */
    SELF doesNotSatisfy(UiTestComponentPredicate predicate);
}
