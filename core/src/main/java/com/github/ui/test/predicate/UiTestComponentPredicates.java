package com.github.ui.test.predicate;

import com.github.ui.test.predicate.playwright.PlaywrightComponentPredicates;
import com.github.ui.test.selector.Selector;

import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class UiTestComponentPredicates {

    private static final PlaywrightComponentPredicates predicates = new PlaywrightComponentPredicates();

    public static UiTestComponentPredicate isVisible() {
        return predicates.isVisible();
    }

    public static UiTestComponentPredicate hasText(String text) {
        return predicates.hasText(text);
    }

    public static UiTestComponentPredicate matches(Pattern pattern) {
        return predicates.matches(pattern);
    }

    public static UiTestComponentPredicate hasChild(String label, Selector childSelector, UiTestComponentPredicate childPredicate) {
        return predicates.hasChild(label, childSelector, childPredicate);
    }

    public static UiTestComponentPredicate allOf(UiTestComponentPredicate predicate, UiTestComponentPredicate... other) {
        if (other.length == 0) {
            return predicate;
        }

        return predicates.allOf(asList(predicate, other));
    }

    public static UiTestComponentPredicate anyOf(UiTestComponentPredicate predicate, UiTestComponentPredicate... other) {
        if (other.length == 0) {
            return predicate;
        }

        return predicates.anyOf(asList(predicate, other));
    }

    public static UiTestComponentPredicate noneOf(UiTestComponentPredicate predicate, UiTestComponentPredicate... other) {
        return anyOf(predicate, other).negate();
    }

    private static List<UiTestComponentPredicate> asList(UiTestComponentPredicate predicate, UiTestComponentPredicate... other) {
        return Stream.concat(
                Stream.of(predicate),
                Stream.of(other)
        ).toList();
    }
}
