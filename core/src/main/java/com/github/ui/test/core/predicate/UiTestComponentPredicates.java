package com.github.ui.test.core.predicate;

import com.github.ui.test.core.selector.Selector;

import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public interface UiTestComponentPredicates {

    UiTestComponentPredicate isVisible();

    UiTestComponentPredicate hasText(String text);

    UiTestComponentPredicate matches(Pattern pattern);

    UiTestComponentPredicate hasChild(String label, Selector childSelector, UiTestComponentPredicate childPredicate);

    default UiTestComponentPredicate allOf(UiTestComponentPredicate predicate, UiTestComponentPredicate... other) {
        if (other.length == 0) {
            return predicate;
        }

        return allOf(asList(predicate, other));
    }

    UiTestComponentPredicate allOf(List<UiTestComponentPredicate> predicates);

    default UiTestComponentPredicate anyOf(UiTestComponentPredicate predicate, UiTestComponentPredicate... other) {
        if (other.length == 0) {
            return predicate;
        }

        return anyOf(asList(predicate, other));
    }

    UiTestComponentPredicate anyOf(List<UiTestComponentPredicate> predicates);

    default UiTestComponentPredicate noneOf(UiTestComponentPredicate predicate, UiTestComponentPredicate... other) {
        return anyOf(predicate, other).negate();
    }

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
