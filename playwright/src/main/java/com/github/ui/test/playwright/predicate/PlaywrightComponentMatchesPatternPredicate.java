package com.github.ui.test.playwright.predicate;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.regex.Pattern;

import static com.github.ui.test.playwright.predicate.PlaywrightComponentHasTextPredicate.describeText;

@Getter
@RequiredArgsConstructor
public class PlaywrightComponentMatchesPatternPredicate implements PlaywrightComponentPredicate {

    private final boolean not;
    private final Pattern pattern;

    public String describePattern() {
        return "matches('" + pattern + "')";
    }

    public String describeNotText() {
        return "not " + describePattern();
    }

    @Override
    public String describeExpected() {
        if (not) {
            return describeNotText();
        } else {
            return describePattern();
        }
    }

    @Override
    public String describeActual(Locator actual) {
        return PlaywrightComponentHasTextPredicate.describeText(actual);
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
