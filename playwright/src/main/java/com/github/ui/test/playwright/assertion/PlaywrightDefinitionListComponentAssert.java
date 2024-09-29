package com.github.ui.test.playwright.assertion;

import com.github.ui.test.core.assertion.HtmlDefinitionListComponentAssert;
import com.github.ui.test.core.component.DefinitionListComponent;
import com.github.ui.test.core.component.UiTestComponent;
import com.github.ui.test.core.predicate.UiTestComponentPredicate;
import com.github.ui.test.playwright.predicate.PlaywrightDefinitionListEntryPredicate;

import static com.github.ui.test.playwright.predicate.PlaywrightComponentPredicateFactory.requirePlaywrightPredicate;

public class PlaywrightDefinitionListComponentAssert<T extends UiTestComponent, D extends UiTestComponent>
        extends AbstractPlaywrightComponentAssert<PlaywrightDefinitionListComponentAssert<T, D>, DefinitionListComponent<T, D>>
        implements HtmlDefinitionListComponentAssert<T, D> {

    public PlaywrightDefinitionListComponentAssert(DefinitionListComponent<T, D> actual) {
        super(actual, PlaywrightDefinitionListComponentAssert.class);
    }

    @Override
    public PlaywrightDefinitionListComponentAssert<T, D> contains(UiTestComponentPredicate title, UiTestComponentPredicate description) {
        return satisfies(new PlaywrightDefinitionListEntryPredicate(false,
                requirePlaywrightPredicate(title),
                requirePlaywrightPredicate(description))
        );
    }
}
