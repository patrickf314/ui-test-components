package com.github.ui.test.playwright.assertion;

import com.github.ui.test.core.assertion.HtmlDefinitionListComponentAssert;
import com.github.ui.test.core.component.DefinitionListComponent;
import com.github.ui.test.core.component.UiTestComponent;
import com.github.ui.test.core.predicate.UiTestComponentPredicate;
import com.github.ui.test.playwright.predicate.PlaywrightComponentHasTextPredicate;
import com.github.ui.test.playwright.predicate.PlaywrightComponentVisiblePredicate;

import static com.github.ui.test.playwright.predicate.PlaywrightComponentPredicates.requirePlaywrightPredicate;

public class PlaywrightDefinitionListComponentAssert<T extends UiTestComponent, D extends UiTestComponent>
        extends AbstractPlaywrightComponentAssert<PlaywrightDefinitionListComponentAssert<T, D>, DefinitionListComponent<T, D>>
        implements HtmlDefinitionListComponentAssert<PlaywrightDefinitionListComponentAssert<T, D>, T, D> {

    public PlaywrightDefinitionListComponentAssert(DefinitionListComponent<T, D> actual) {
        super(actual, PlaywrightDefinitionListComponentAssert.class);
    }

    @Override
    protected PlaywrightDefinitionListComponentAssert<T, D> self() {
        return this;
    }

    @Override
    public PlaywrightDefinitionListComponentAssert<T, D> contains(UiTestComponentPredicate title, UiTestComponentPredicate description) {
        var titlePredicate = requirePlaywrightPredicate(title);
        var titleLocator = getActualLocator().locator("dt");

        var matchingTitles = titlePredicate.filter(getActualPage(), titleLocator);

        try {
            locatorAssertions(matchingTitles).not().hasCount(0);
        } catch (AssertionError e) {
            throw withActualExpected(e, titlePredicate.describeActual(titleLocator), titlePredicate.describeExpected());
        }

        var descriptionCandidates = matchingTitles.locator("xpath=following-sibling::dd[1]");
        if (description instanceof PlaywrightComponentHasTextPredicate hasTextPredicate) {
            if (hasTextPredicate.isNot()) {
                locatorAssertions(descriptionCandidates).not().hasText(hasTextPredicate.getText());
            } else {
                locatorAssertions(descriptionCandidates).hasText(hasTextPredicate.getText());
            }
        } else if (description instanceof PlaywrightComponentVisiblePredicate visiblePredicate) {
            if (visiblePredicate.isNot()) {
                locatorAssertions(descriptionCandidates).not().isVisible();
            } else {
                locatorAssertions(descriptionCandidates).isVisible();
            }
        } else {
            var matchingDescriptions = requirePlaywrightPredicate(description).filter(getActualPage(), descriptionCandidates);
            locatorAssertions(matchingDescriptions).not().hasCount(0);
        }

        return this;
    }
}
