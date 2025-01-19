package com.github.ui.test.core.assertion;

import com.github.ui.test.core.component.HtmlDefinitionListComponent;
import com.github.ui.test.core.component.UiTestComponent;
import com.github.ui.test.core.predicate.UiTestComponentPredicate;

/**
 * Extension of the {@link GenericUiTestComponentAssert} for {@link HtmlDefinitionListComponent}
 *
 * @param <T> the component type of the title element
 * @param <D> the component type of the description element
 */
public interface HtmlDefinitionListComponentAssert<T extends UiTestComponent, D extends UiTestComponent>
        extends GenericUiTestComponentAssert<HtmlDefinitionListComponentAssert<T, D>, HtmlDefinitionListComponent<T, D>> {

    /**
     * Assert that the definition list contains an entry with the given title and the given description
     *
     * @param title       the expected title
     * @param description the expected description
     * @return this
     */
    default HtmlDefinitionListComponentAssert<T, D> contains(String title, String description) {
        return contains(title, UiTestComponentPredicate.hasText(description));
    }

    /**
     * Assert that the definition list contains an entry with the given title and matching the description predicate
     *
     * @param title       the expected title
     * @param description the expected description predicate
     * @return this
     */
    default HtmlDefinitionListComponentAssert<T, D> contains(String title, UiTestComponentPredicate description) {
        return contains(UiTestComponentPredicate.hasText(title), description);
    }

    /**
     * Assert that the definition list contains an entry with the matching the title and the description predicate
     *
     * @param title       the expected title predicate
     * @param description the expected description predicate
     * @return this
     */
    HtmlDefinitionListComponentAssert<T, D> contains(UiTestComponentPredicate title, UiTestComponentPredicate description);
}
