package com.github.ui.test.assertion;

import com.github.ui.test.component.DefinitionListComponent;
import com.github.ui.test.component.UiTestComponent;
import com.github.ui.test.predicate.UiTestComponentPredicate;
import com.github.ui.test.predicate.UiTestComponentPredicates;

public interface HtmlDefinitionListComponentAssert<SELF extends HtmlDefinitionListComponentAssert<SELF, T, D>, T extends UiTestComponent, D extends UiTestComponent>
        extends UiTestComponentAssert<SELF, DefinitionListComponent<T, D>> {

    default SELF contains(String title, String description) {
        return contains(title, UiTestComponentPredicates.hasText(description));
    }

    default SELF contains(String title, UiTestComponentPredicate description) {
        return contains(UiTestComponentPredicates.hasText(title), description);
    }

    SELF contains(UiTestComponentPredicate title, UiTestComponentPredicate description);
}
