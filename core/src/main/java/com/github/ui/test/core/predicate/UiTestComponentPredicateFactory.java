package com.github.ui.test.core.predicate;

import com.github.ui.test.core.selector.Selector;

import java.util.List;
import java.util.regex.Pattern;

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
     * Matches elements which have the given attribute.
     * The predicate matches only if the attribute is present and not the actual value
     * of the attribute
     *
     * @param attribute the attribute name
     * @return the predicate
     * @see #hasAttribute(String, String)
     */
    UiTestComponentPredicate hasAttribute(String attribute);

    /**
     * Matches elements which have the given attribute with the specific value.
     *
     * @param attribute the attribute name
     * @return the predicate
     * @see #hasAttribute(String, String)
     */
    UiTestComponentPredicate hasAttribute(String attribute, String value);

    /**
     * Matches an {@link com.github.ui.test.core.component.HtmlDefinitionListComponent.Entry} in an HTML definition list
     * (in {@code <dl>...</dl>} with a title ({@code <dt>})
     * matching the titlePredicate and a description ({@code <dd>}) matching the descriptionPredicate.
     *
     * @param titlePredicate       the title predicate
     * @param descriptionPredicate the description predicate
     * @return the entry predicate matching the combination of title and description predicate
     */
    UiTestComponentPredicate descriptionListEntry(UiTestComponentPredicate titlePredicate, UiTestComponentPredicate descriptionPredicate);

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
     * @param predicates a list of predicate to combine
     * @return the combined predicates
     */
    UiTestComponentPredicate anyOf(List<UiTestComponentPredicate> predicates);

    /**
     * Combines multiple predicate to one requiring that the element does not match any of the given predicate
     *
     * @param predicates a list of predicates
     * @return the combined predicates
     */
    default UiTestComponentPredicate noneOf(List<UiTestComponentPredicate> predicates) {
        return anyOf(predicates).negate();
    }
}
