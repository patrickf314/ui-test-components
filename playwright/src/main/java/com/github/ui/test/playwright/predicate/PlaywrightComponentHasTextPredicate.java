package com.github.ui.test.playwright.predicate;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.stream.Collectors;

@Getter
@RequiredArgsConstructor
public class PlaywrightComponentHasTextPredicate implements PlaywrightComponentPredicate {

    private final boolean not;
    private final String text;

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
        var filterOptions = new Locator.FilterOptions();
        if (not) {
            filterOptions.setHasNotText(text);
        } else {
            filterOptions.setHasText(text);
        }
        return locator.filter(filterOptions);
    }
}
