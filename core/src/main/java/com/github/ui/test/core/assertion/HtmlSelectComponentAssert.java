package com.github.ui.test.core.assertion;

import com.github.ui.test.core.component.HtmlSelectComponent;
import com.github.ui.test.core.predicate.UiTestComponentPredicate;

import java.util.stream.Stream;

/**
 * Extension of the {@link GenericUiTestComponentAssert} for {@link HtmlSelectComponent}
 */
public interface HtmlSelectComponentAssert extends GenericUiTestComponentAssert<HtmlSelectComponentAssert, HtmlSelectComponent> {

    /**
     * Assert that the select component contains the given options, in any order.
     *
     * @param options the expected options
     * @return this
     */
    default HtmlSelectComponentAssert containsOptions(String... options) {
        if (options.length == 0) {
            return this;
        }

        Stream.of(options).map(UiTestComponentPredicate::hasText).forEach(this::containsOption);
        return this;
    }

    /**
     * Assert that the select component has an option matching the given predicate
     *
     * @param predicate the option predicate
     * @return this
     */
    HtmlSelectComponentAssert containsOption(UiTestComponentPredicate predicate);

}
