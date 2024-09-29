package com.github.ui.test.playwright.predicate;

import com.github.ui.test.core.exception.IllegalContextException;
import com.github.ui.test.core.predicate.UiTestComponentPredicate;
import com.github.ui.test.core.predicate.UiTestComponentPredicateFactory;
import com.github.ui.test.core.selector.Selector;
import com.github.ui.test.playwright.selector.PlaywrightSelectorFactory;

import java.util.List;
import java.util.regex.Pattern;

public class PlaywrightComponentPredicateFactory implements UiTestComponentPredicateFactory {

    @Override
    public PlaywrightComponentPredicate isVisible() {
        return new PlaywrightComponentVisiblePredicate(false);
    }

    @Override
    public PlaywrightComponentPredicate hasText(String text) {
        return new PlaywrightComponentHasTextPredicate(false, text);
    }

    @Override
    public PlaywrightComponentPredicate matches(Pattern pattern) {
        return new PlaywrightComponentMatchesPatternPredicate(false, pattern);
    }

    @Override
    public PlaywrightComponentPredicate hasChild(String label, Selector childSelector, UiTestComponentPredicate childPredicate) {
        return new PlaywrightChildComponentPredicate(label, false, PlaywrightSelectorFactory.requirePlaywrightSelector(childSelector), requirePlaywrightPredicate(childPredicate));
    }

    @Override
    public PlaywrightComponentPredicate allOf(List<UiTestComponentPredicate> predicates) {
        return new PlaywrightComponentAllOfPredicate(predicates.stream().map(PlaywrightComponentPredicateFactory::requirePlaywrightPredicate).toList());
    }

    @Override
    public PlaywrightComponentPredicate anyOf(List<UiTestComponentPredicate> predicates) {
        return new PlaywrightComponentAnyOfPredicate(predicates.stream().map(PlaywrightComponentPredicateFactory::requirePlaywrightPredicate).toList());
    }

    @Override
    public PlaywrightComponentPredicate descriptionListEntry(UiTestComponentPredicate titlePredicate, UiTestComponentPredicate descriptionPredicate) {
        return new PlaywrightDefinitionListEntryPredicate(false, requirePlaywrightPredicate(titlePredicate), requirePlaywrightPredicate(descriptionPredicate));
    }

    public static PlaywrightComponentPredicate requirePlaywrightPredicate(UiTestComponentPredicate predicate) {
        if (predicate instanceof PlaywrightComponentPredicate playwrightPredicate) {
            return playwrightPredicate;
        }

        throw new IllegalContextException("playwright", "predicate of type " + predicate.getClass().getName());
    }
}
