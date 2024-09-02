package com.github.ui.test.predicate.playwright;

import com.github.ui.test.exception.IllegalContextException;
import com.github.ui.test.predicate.UiTestComponentPredicate;
import com.github.ui.test.selector.Selector;

import java.util.List;
import java.util.regex.Pattern;

import static com.github.ui.test.selector.playwright.PlaywrightSelectors.requirePlaywrightSelector;

public class PlaywrightComponentPredicates {

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
        return new PlaywrightChildComponentPredicate(label, false, requirePlaywrightSelector(childSelector), requirePlaywrightPredicate(childPredicate));
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
