package com.github.ui.test.core.assertion;

import com.github.ui.test.core.component.DefinitionListComponent;
import com.github.ui.test.core.component.UiTestComponent;
import com.github.ui.test.core.predicate.UiTestComponentPredicate;

public interface HtmlDefinitionListComponentAssert<T extends UiTestComponent, D extends UiTestComponent>
        extends UiTestComponentAssert<DefinitionListComponent<T, D>> {

    default HtmlDefinitionListComponentAssert<T, D> contains(String title, String description) {
        return contains(title, UiTestComponentPredicate.hasText(description));
    }

    default HtmlDefinitionListComponentAssert<T, D> contains(String title, UiTestComponentPredicate description) {
        return contains(UiTestComponentPredicate.hasText(title), description);
    }

    HtmlDefinitionListComponentAssert<T, D> contains(UiTestComponentPredicate title, UiTestComponentPredicate description);
}
