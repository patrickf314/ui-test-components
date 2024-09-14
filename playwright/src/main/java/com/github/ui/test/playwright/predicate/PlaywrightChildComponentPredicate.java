package com.github.ui.test.playwright.predicate;

import com.github.ui.test.playwright.selector.PlaywrightSelector;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class PlaywrightChildComponentPredicate implements PlaywrightComponentPredicate {

    private final String label;
    private final boolean not;
    private final PlaywrightSelector childSelector;
    private final PlaywrightComponentPredicate childPredicate;

    @Override
    public String describeExpected() {
        return label + " = " + (not ? "not " : "") + childPredicate.describeExpected();
    }

    @Override
    public String describeActual(Locator actual) {
        var childLocator = childSelector.asChildLocatorOf(actual);
        var childCount = childLocator.count();
        if (childCount == 0) {
            return label + " = <NOT FOUND>";
        }

        if (childCount == 1) {
            return label + " = " + childPredicate.describeActual(childLocator);
        }

        var builder = new StringBuilder(label).append(" = [\n");
        for (var child : childLocator.all()) {
            builder.append("\t").append(childPredicate.describeActual(child)).append("\n");
        }
        return builder.append("]").toString();
    }

    @Override
    public PlaywrightComponentPredicate negate() {
        return new PlaywrightChildComponentPredicate(label, !not, childSelector, childPredicate);
    }

    @Override
    public Locator filter(Page page, Locator parentLocator) {
        var childLocator = childPredicate.filter(page, childSelector.locateOn(page));
        var filterOptions = new Locator.FilterOptions();
        if (not) {
            filterOptions.setHasNot(childLocator);
        } else {
            filterOptions.setHas(childLocator);
        }
        return parentLocator.filter(filterOptions);
    }
}
