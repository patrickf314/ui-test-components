package com.github.ui.test.playwright.predicate;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import lombok.RequiredArgsConstructor;

import java.util.stream.Collectors;

@RequiredArgsConstructor
public class PlaywrightComponentHasAttributePredicate implements PlaywrightComponentPredicate {

    private final boolean not;
    private final String attribute;
    private final String value;

    public static String describeAttribute(String attribute, String value) {
        return "attribute(" + attribute + (value == null ? "" : ", '" + value + "'") + ")";
    }

    public static String describeNotAttribute(String attribute, String value) {
        return "not " + describeAttribute(attribute, value);
    }

    public static String describeAttribute(String attribute, Locator actual) {
        var count = actual.count();
        if (count == 0) {
            return "<NOT FOUND>";
        }

        if (count == 1) {
            return describeAttribute(attribute, actual.getAttribute(attribute));
        }

        return "[" + actual.all().stream().map(item -> describeAttribute(attribute, item)).collect(Collectors.joining(",")) + "]";
    }

    @Override
    public String describeActual(Locator actual) {
        return describeAttribute(attribute, actual);
    }

    @Override
    public String describeExpected() {
        if (not) {
            return describeNotAttribute(attribute, value);
        } else {
            return describeAttribute(attribute, value);
        }
    }

    @Override
    public PlaywrightComponentPredicate negate() {
        return new PlaywrightComponentHasAttributePredicate(!not, attribute, value);
    }

    @Override
    public Locator filter(Page page, Locator locator) {
        var cssSelector = ":scope" + (value == null ? "[" + attribute + "]" : "[" + attribute + "='" + value + "']");
        var attributeLocator = page.locator(cssSelector);
        var filterOptions = new Locator.FilterOptions();
        if (not) {
            filterOptions.setHasNot(attributeLocator);
        } else {
            filterOptions.setHas(attributeLocator);
        }
        return locator.filter(filterOptions);
    }
}
