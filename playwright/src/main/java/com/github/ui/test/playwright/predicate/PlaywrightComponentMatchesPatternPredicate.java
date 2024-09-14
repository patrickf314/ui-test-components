package com.github.ui.test.playwright.predicate;

import com.github.ui.test.core.predicate.UiTestComponentPredicate;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

import java.util.regex.Pattern;

/**
 * Implementation of a {@link UiTestComponentPredicate} which matches
 * the text of a {@link com.github.ui.test.core.component.UiTestComponent}.
 * This predicate matches the text using a RegExp {@link Pattern}
 *
 * @see PlaywrightComponentContainsTextPredicate
 */
public class PlaywrightComponentMatchesPatternPredicate extends AbstractPlaywrightComponentTextPredicate {

    private final Pattern pattern;

    /**
     * Constructor
     *
     * @param not     true if the predicate is negated
     * @param pattern the pattern to match
     */
    public PlaywrightComponentMatchesPatternPredicate(boolean not, Pattern pattern) {
        super(not);

        this.pattern = pattern;
    }

    @Override
    public String describeExpected() {
        if (not) {
            return "does not match('" + pattern + "')";
        } else {
            return "matches('" + pattern + "')";
        }
    }

    @Override
    public PlaywrightComponentPredicate negate() {
        return new PlaywrightComponentMatchesPatternPredicate(!not, pattern);
    }

    @Override
    public Locator filter(Page page, Locator locator) {
        var filterOptions = new Locator.FilterOptions();
        if (not) {
            filterOptions.setHasNotText(pattern);
        } else {
            filterOptions.setHasText(pattern);
        }
        return locator.filter(filterOptions);
    }
}
