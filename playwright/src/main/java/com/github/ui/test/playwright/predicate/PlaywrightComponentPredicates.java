package com.github.ui.test.playwright.predicate;

import com.github.ui.test.core.exception.IllegalContextException;
import com.github.ui.test.core.predicate.UiTestComponentPredicate;
import com.github.ui.test.core.predicate.UiTestComponentPredicates;
import com.github.ui.test.core.selector.Selector;
import com.github.ui.test.playwright.selector.PlaywrightSelectors;

import java.util.List;
import java.util.regex.Pattern;

public class PlaywrightComponentPredicates implements UiTestComponentPredicates {

    public PlaywrightComponentPredicate isVisible() {
        return new PlaywrightComponentVisiblePredicate(false);
    }

    public PlaywrightComponentPredicate hasText(String text) {
        return new PlaywrightComponentHasTextPredicate(false, text);
    }

    public PlaywrightComponentPredicate matches(Pattern pattern) {
        return new PlaywrightComponentMatchesPatternPredicate(false, pattern);
    }

    public PlaywrightComponentPredicate hasChild(String label, Selector childSelector, UiTestComponentPredicate childPredicate) {
        return new PlaywrightChildComponentPredicate(label, false, PlaywrightSelectors.requirePlaywrightSelector(childSelector), requirePlaywrightPredicate(childPredicate));
    }

    public PlaywrightComponentPredicate allOf(List<UiTestComponentPredicate> predicates) {
        return new PlaywrightComponentAllOfPredicate(predicates.stream().map(PlaywrightComponentPredicates::requirePlaywrightPredicate).toList());
    }

    public PlaywrightComponentPredicate anyOf(List<UiTestComponentPredicate> predicates) {
        return new PlaywrightComponentAnyOfPredicate(predicates.stream().map(PlaywrightComponentPredicates::requirePlaywrightPredicate).toList());
    }

    public static PlaywrightComponentPredicate requirePlaywrightPredicate(UiTestComponentPredicate predicate) {
        if (predicate instanceof PlaywrightComponentPredicate playwrightPredicate) {
            return playwrightPredicate;
        }

        throw new IllegalContextException("playwright", "predicate of type " + predicate.getClass().getName());
    }
}
