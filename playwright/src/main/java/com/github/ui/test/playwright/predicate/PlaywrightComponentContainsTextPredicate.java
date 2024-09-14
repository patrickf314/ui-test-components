package com.github.ui.test.playwright.predicate;

import com.github.ui.test.core.predicate.UiTestComponentPredicate;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

/**
 * Implementation of a {@link UiTestComponentPredicate} which matches
 * the text of a {@link com.github.ui.test.core.component.UiTestComponent}
 * This predicate matches if the element contains a specific text.
 * The matching is case-insensitive and searches for a substring.
 *
 * @see PlaywrightComponentContainsTextPredicate
 */
public class PlaywrightComponentContainsTextPredicate extends AbstractPlaywrightComponentTextPredicate {

    private final String text;

    /**
     * Constructor
     *
     * @param not  true if the predicate is negated
     * @param text the text to match
     */
    public PlaywrightComponentContainsTextPredicate(boolean not, String text) {
        super(not);

        this.text = text;
    }

    @Override
    public String describeExpected() {
        if (not) {
            return "does not contain " + describeText(text);
        } else {
            return "contains " + describeText(text);
        }
    }

    @Override
    public PlaywrightComponentPredicate negate() {
        return new PlaywrightComponentContainsTextPredicate(!not, text);
    }

    @Override
    public Locator filter(Page page, Locator locator) {
        var filterOptions = new Locator.FilterOptions();
        if (not) {
            filterOptions.setHasNotText(text);
        } else {
            filterOptions.setHasText(text);
        }
        return locator.filter(filterOptions);
    }
}
