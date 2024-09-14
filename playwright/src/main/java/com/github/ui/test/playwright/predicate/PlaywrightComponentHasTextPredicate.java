package com.github.ui.test.playwright.predicate;

import com.github.ui.test.core.predicate.UiTestComponentPredicate;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

import java.util.stream.Collectors;

/**
 * Implementation of a {@link UiTestComponentPredicate} which matches
 * the text of a {@link com.github.ui.test.core.component.UiTestComponent}.
 * This predicate matches the text exactly: case-sensitive and no substring matching.
 *
 * @see PlaywrightComponentContainsTextPredicate
 */
public class PlaywrightComponentHasTextPredicate extends AbstractPlaywrightComponentTextPredicate {

    private final String text;

    /**
     * Constructor
     *
     * @param not  true if the predicate is negated
     * @param text the text to match
     */
    public PlaywrightComponentHasTextPredicate(boolean not, String text) {
        super(not);

        this.text = text;
    }

    public static String describeText(String text) {
        return "text('" + text.replace("\n", "\\n") + "')";
    }

    public static String describeNotText(String text) {
        return "not " + describeText(text);
    }

    public static String describeText(Locator actual) {
        var count = actual.count();
        if (count == 0) {
            return "<NOT FOUND>";
        }

        if (count == 1) {
            return describeText(actual.textContent());
        }

        return "[" + actual.all().stream().map(PlaywrightComponentHasTextPredicate::describeText).collect(Collectors.joining(",")) + "]";
    }

    @Override
    public String describeExpected() {
        if (not) {
            return describeNotText(text);
        } else {
            return describeText(text);
        }
    }

    @Override
    public String describeActual(Locator actual) {
        return describeText(actual);
    }

    @Override
    public PlaywrightComponentPredicate negate() {
        return new PlaywrightComponentHasTextPredicate(!not, text);
    }

    @Override
    public Locator filter(Page page, Locator locator) {
        var locatorWithText = page.getByText(text, new Page.GetByTextOptions().setExact(true));
        var filterOptions = new Locator.FilterOptions();
        if (not) {
            filterOptions.setHasNot(locatorWithText);
        } else {
            filterOptions.setHas(locatorWithText);
        }
        return locator.filter(filterOptions);
    }
}
