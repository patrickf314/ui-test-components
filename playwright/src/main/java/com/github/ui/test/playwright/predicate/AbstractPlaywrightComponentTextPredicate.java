package com.github.ui.test.playwright.predicate;

import com.github.ui.test.core.predicate.UiTestComponentPredicate;
import com.microsoft.playwright.Locator;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.stream.Collectors;

/**
 * Abstract implementation of a {@link UiTestComponentPredicate} which matches
 * the text of a {@link com.github.ui.test.core.component.UiTestComponent}.
 *
 * @see PlaywrightComponentHasTextPredicate
 * @see PlaywrightComponentContainsTextPredicate
 */
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public abstract class AbstractPlaywrightComponentTextPredicate implements PlaywrightComponentPredicate {

    protected final boolean not;

    /**
     * Describes the given text.
     *
     * @param text the text to describe
     * @return the text description
     */
    protected static String describeText(String text) {
        return "text('" + text.replace("\n", "\\n") + "')";
    }

    @Override
    public String describeActual(Locator actual) {
        var count = actual.count();
        if (count == 0) {
            return "<NOT FOUND>";
        }

        if (count == 1) {
            return describeText(actual.textContent());
        }

        return "[" + actual.all().stream().map(this::describeActual).collect(Collectors.joining(",")) + "]";
    }
}
