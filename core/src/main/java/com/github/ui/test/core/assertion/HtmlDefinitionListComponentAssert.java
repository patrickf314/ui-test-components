package com.github.ui.test.core.assertion;

import com.github.ui.test.core.UiTestEnvironment;
import com.github.ui.test.core.component.DefinitionListComponent;
import com.github.ui.test.core.component.UiTestComponent;
import com.github.ui.test.core.predicate.UiTestComponentPredicate;

public interface HtmlDefinitionListComponentAssert<SELF extends HtmlDefinitionListComponentAssert<SELF, T, D>, T extends UiTestComponent, D extends UiTestComponent>
        extends UiTestComponentAssert<SELF, DefinitionListComponent<T, D>> {

    default SELF contains(String title, String description) {
        return contains(title, UiTestEnvironment.hasText(description));
    }

    default SELF contains(String title, UiTestComponentPredicate description) {
        return contains(UiTestEnvironment.hasText(title), description);
    }

    SELF contains(UiTestComponentPredicate title, UiTestComponentPredicate description);
}
