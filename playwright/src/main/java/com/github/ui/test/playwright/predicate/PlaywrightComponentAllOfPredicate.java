package com.github.ui.test.playwright.predicate;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.github.ui.test.core.predicate.UiTestComponentPredicate;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@RequiredArgsConstructor
public class PlaywrightComponentAllOfPredicate implements PlaywrightComponentPredicate {

    private final List<PlaywrightComponentPredicate> predicates;

    @Override
    public String describeExpected() {
        return "allOf(\n" +
               predicates.stream().map(predicate -> " - " + predicate.describeExpected()).collect(Collectors.joining("\n"))
               + "\n)";
    }

    @Override
    public String describeActual(Locator actual) {
        var count = actual.count();
        if (count == 0) {
            return "<NOT FOUND>";
        }

        if (count == 1) {
            return "allOf(\n" +
                   predicates.stream().map(predicate -> " - " + predicate.describeActual(actual)).collect(Collectors.joining("\n"))
                   + "\n)";
        }

        return "[" + actual.all().stream().map(this::describeActual).collect(Collectors.joining("\n")) + "]";
    }

    @Override
    public PlaywrightComponentPredicate negate() {
        return new PlaywrightComponentAnyOfPredicate(
                predicates.stream().map(PlaywrightComponentPredicate::negate).toList()
        );
    }

    @Override
    public PlaywrightComponentPredicate and(UiTestComponentPredicate other) {
        var copy = new ArrayList<>(predicates);
        copy.add(PlaywrightComponentPredicates.requirePlaywrightPredicate(other));
        return new PlaywrightComponentAllOfPredicate(copy);
    }

    @Override
    public Locator filter(Page page, Locator locator) {
        var filteredLocator = locator;
        for (var predicate : predicates) {
            filteredLocator = predicate.filter(page, filteredLocator);
        }
        return filteredLocator;
    }
}
