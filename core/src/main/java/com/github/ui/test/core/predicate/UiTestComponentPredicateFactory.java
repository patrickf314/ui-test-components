package com.github.ui.test.core.predicate;

import com.github.ui.test.core.selector.Selector;

import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Stream;

/**
 * A factory for creating {@link UiTestComponentPredicate}s.
 */
public interface UiTestComponentPredicateFactory {

    /**
     * Matches elements which are visible on the HTML site.
     *
     * @return the predicate
     */
    UiTestComponentPredicate isVisible();

    /**
     * Matches elements which have the given text.
     * This predicate matches the text content exactly,
     * case-sensitive and not substring matching.
     *
     * @param text the text
     * @return the predicate
     */
    UiTestComponentPredicate hasText(String text);

    /**
     * Matches elements which have a text matching the given pattern.
     *
     * @param pattern the pattern
     * @return the predicate
     */
    UiTestComponentPredicate matches(Pattern pattern);

    /**
     * Matches elements which contain a child matching a predicate
     *
     * @param label          a label of the child element (used for error messages only)
     * @param childSelector  the selector of the child element (relative to the matched element)
     * @param childPredicate the predicate, which the child element has to match
     * @return the predicate
     */
    UiTestComponentPredicate hasChild(String label, Selector childSelector, UiTestComponentPredicate childPredicate);

    /**
     * Combines multiple predicate to one using a logical and.
     *
     * @param predicate a first predicate
     * @param other     other predicates
     * @return the combined predicates
     */
    default UiTestComponentPredicate allOf(UiTestComponentPredicate predicate, UiTestComponentPredicate... other) {
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
    UiTestComponentPredicate allOf(List<UiTestComponentPredicate> predicates);

    /**
     * Combines multiple predicate to one using a logical or.
     *
     * @param predicate a first predicate
     * @param other     other predicates
     * @return the combined predicates
     */
    default UiTestComponentPredicate anyOf(UiTestComponentPredicate predicate, UiTestComponentPredicate... other) {
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
    UiTestComponentPredicate anyOf(List<UiTestComponentPredicate> predicates);

    /**
     * Combines multiple predicate to one requiring that the element does not match any of the given predicate
     *
     * @param predicate a first predicate
     * @param other     other predicates
     * @return the combined predicates
     */
    default UiTestComponentPredicate noneOf(UiTestComponentPredicate predicate, UiTestComponentPredicate... other) {
        return anyOf(predicate, other).negate();
    }

    /**
     * Combines multiple predicate to one requiring that the element does not match any of the given predicate
     *
     * @param predicates a list of predicates
     * @return the combined predicates
     */
    default UiTestComponentPredicate noneOf(List<UiTestComponentPredicate> predicates) {
        return anyOf(predicates).negate();
    }

    private static List<UiTestComponentPredicate> asList(UiTestComponentPredicate predicate, UiTestComponentPredicate... other) {
        return Stream.concat(
                Stream.of(predicate),
                Stream.of(other)
        ).toList();
    }
}
