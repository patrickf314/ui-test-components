package com.github.ui.test.core.predicate;

import com.github.ui.test.core.context.UiTestComponentContext;
import com.github.ui.test.core.selector.Selector;

import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Stream;

import static com.github.ui.test.core.UiTestEnvironment.getEnvironment;

/**
 * A predicate for matching {@link com.github.ui.test.core.component.UiTestComponent}s.
 * In contrast to the standard java {@link java.util.function.Predicate}s, these predicates
 * can only be used for assertions. The actual matching will always include the necessary waiting.
 */
public interface UiTestComponentPredicate {

    /**
     * Matches elements which are visible on the HTML site.
     *
     * @return the predicate
     */
    static UiTestComponentPredicate isVisible() {
        return getEnvironment().getPredicateFactory().isVisible();
    }

    /**
     * Matches elements which have the given text.
     *
     * @param text the text
     * @return the predicate
     */
    static UiTestComponentPredicate hasText(String text) {
        return getEnvironment().getPredicateFactory().hasText(text);
    }

    /**
     * Matches elements which have a text matching the given pattern.
     *
     * @param pattern the pattern
     * @return the predicate
     */
    static UiTestComponentPredicate matches(Pattern pattern) {
        return getEnvironment().getPredicateFactory().matches(pattern);
    }

    /**
     * Matches elements which contain a child matching a predicate
     *
     * @param label          a label of the child element (used for error messages only)
     * @param childSelector  the selector of the child element (relative to the matched element)
     * @param childPredicate the predicate, which the child element has to match
     * @return the predicate
     */
    static UiTestComponentPredicate hasChild(String label, Selector childSelector, UiTestComponentPredicate childPredicate) {
        return getEnvironment().getPredicateFactory().hasChild(label, childSelector, childPredicate);
    }

    /**
     * Combines multiple predicate to one using a logical and.
     *
     * @param predicate a first predicate
     * @param other     other predicates
     * @return the combined predicates
     */
    static UiTestComponentPredicate allOf(UiTestComponentPredicate predicate, UiTestComponentPredicate... other) {
        if (other.length == 0) {
            return predicate;
        }

        return allOf(asList(predicate, other));
    }

    /**
     * Combines multiple predicate to one using a logical and.
     *
     * @param predicates a list of predicate to combine
     * @return the combined predicates
     */
    static UiTestComponentPredicate allOf(List<UiTestComponentPredicate> predicates) {
        return getEnvironment().getPredicateFactory().allOf(predicates);
    }

    /**
     * Combines multiple predicate to one using a logical or.
     *
     * @param predicate a first predicate
     * @param other     other predicates
     * @return the combined predicates
     */
    static UiTestComponentPredicate anyOf(UiTestComponentPredicate predicate, UiTestComponentPredicate... other) {
        if (other.length == 0) {
            return predicate;
        }

        return anyOf(asList(predicate, other));
    }

    /**
     * Combines multiple predicate to one using a logical or.
     *
     * @param predicates a list of predicate to combine
     * @return the combined predicates
     */
    static UiTestComponentPredicate anyOf(List<UiTestComponentPredicate> predicates) {
        return getEnvironment().getPredicateFactory().anyOf(predicates);
    }

    /**
     * Combines multiple predicate to one requiring that the element does not match any of the given predicate
     *
     * @param predicate a first predicate
     * @param other     other predicates
     * @return the combined predicates
     */
    static UiTestComponentPredicate noneOf(UiTestComponentPredicate predicate, UiTestComponentPredicate... other) {
        return noneOf(asList(predicate, other));
    }

    /**
     * Combines multiple predicate to one requiring that the element does not match any of the given predicate
     *
     * @param predicates a list of predicate to combine
     * @return the combined predicates
     */
    static UiTestComponentPredicate noneOf(List<UiTestComponentPredicate> predicates) {
        return noneOf(predicates).negate();
    }

    static UiTestComponentPredicate descriptionListEntry(String title, UiTestComponentPredicate descriptionPredicate) {
        return descriptionListEntry(hasText(title), descriptionPredicate);
    }

    static UiTestComponentPredicate descriptionListEntry(UiTestComponentPredicate titlePredicate, UiTestComponentPredicate descriptionPredicate) {
        return getEnvironment().getPredicateFactory().descriptionListEntry(titlePredicate, descriptionPredicate);
    }

    private static List<UiTestComponentPredicate> asList(UiTestComponentPredicate predicate, UiTestComponentPredicate... other) {
        return Stream.concat(
                Stream.of(predicate),
                Stream.of(other)
        ).toList();
    }

    /**
     * Describes the expected state of a {@link UiTestComponentContext}
     *
     * @return the description
     */
    String describeExpected();

    /**
     * Describes the actual state of a {@link UiTestComponentContext}
     *
     * @param actual the actual context
     * @return the description
     */
    String describeActual(UiTestComponentContext actual);

    /**
     * Negates this predicate.
     *
     * @return the negated predicate
     */
    UiTestComponentPredicate negate();

    /**
     * Combines this predicate with another predicate to one requiring that
     * both predicates are satisfied.
     *
     * @param other the other predicate
     * @return the combined predicate
     */
    default UiTestComponentPredicate and(UiTestComponentPredicate other) {
        return allOf(this, other);
    }

    /**
     * Combines this predicate with another predicate to one requiring that
     * at least one of both predicates is satisfied.
     *
     * @param other the other predicate
     * @return the combined predicate
     */
    default UiTestComponentPredicate or(UiTestComponentPredicate other) {
        return anyOf(this, other);
    }
}
