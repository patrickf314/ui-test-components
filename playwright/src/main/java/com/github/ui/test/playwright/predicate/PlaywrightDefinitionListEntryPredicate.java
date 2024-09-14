package com.github.ui.test.playwright.predicate;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;

/**
 * Implementation of a {@link com.github.ui.test.core.predicate.UiTestComponentPredicate}
 * matching the entries of a HTML definition list.
 * <p>
 * This predicate assume, that the DOM has the form
 * {@code
 *     <dl>
 *         <dt>Some title</dt>
 *         <dd>Description of this title</dd>
 *         <dt>Another title</dt>
 *         <dd>Description of the other title</dd>
 *         ...
 *     </dl>
 * }.
 * The predicate considers a dt element and the following sibling (which must be a dd element)
 * as an entry.
 * Using this predicate allows matching title and description of a single entry, which differs from
 * matching two lists (one of the titles and one of the description) as this predicate ensures, that
 * only the next sibling of a matching title is considered for matching the description.
 */
@RequiredArgsConstructor
public class PlaywrightDefinitionListEntryPredicate implements PlaywrightComponentPredicate {

    private final boolean not;
    private final PlaywrightComponentPredicate titlePredicate;
    private final PlaywrightComponentPredicate descriptionPredicate;

    @Override
    public String describeActual(Locator actual) {
        var actualTitles = actual.locator("dt");
        var actualDescriptions = actualTitles.locator("xpath=following-sibling::dd[1]");

        var entries = new ArrayList<String>();
        for (var i = 0; i < actualTitles.count(); i++) {
            entries.add("entry(" + titlePredicate.describeActual(actualTitles.nth(i)) + ", " + descriptionPredicate.describeActual(actualDescriptions.nth(i)) + ")");
        }

        return "[" + String.join(", ", entries) + ")";

    }

    @Override
    public String describeExpected() {
        return (not ? "not has" : "has") + " entry(" + titlePredicate.describeExpected() + ", " + descriptionPredicate.describeExpected() + ")";
    }

    @Override
    public PlaywrightComponentPredicate negate() {
        return new PlaywrightDefinitionListEntryPredicate(!not, titlePredicate, descriptionPredicate);
    }

    @Override
    public Locator filter(Page page, Locator locator) {
        var titleLocator = page.locator("dt");
        var matchingTitles = titlePredicate.filter(page, titleLocator);
        var descriptionCandidates = matchingTitles.locator("xpath=following-sibling::dd[1]");
        var matchingDescriptions = descriptionPredicate.filter(page, descriptionCandidates);

        var options = new Locator.FilterOptions();
        if (not) {
            options.setHasNot(matchingDescriptions);
        } else {
            options.setHas(matchingDescriptions);
        }

        return locator.filter(options);
    }
}

