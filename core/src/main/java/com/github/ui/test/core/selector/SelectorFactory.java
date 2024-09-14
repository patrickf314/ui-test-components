package com.github.ui.test.core.selector;

import java.util.List;
import java.util.stream.Stream;

/**
 * A factory for {@link Selector}s providing functions
 * to create {@link Selector}s based on common expression
 */
public interface SelectorFactory {

    /**
     * Creates a {@link Selector} from a CSS selector
     *
     * @param cssSelector the CSS selector
     * @return the selector
     */
    Selector byCSSSelector(String cssSelector);

    /**
     * Creates a {@link Selector} which selects elements by its text content.
     *
     * @param text the text content
     * @return the selector
     */
    Selector byText(String text);

    /**
     * Creates a {@link Selector} which selects elements by the test id attribute.
     *
     * @param testId the test id
     * @return the selector
     */
    Selector byTestId(String testId);

    /**
     * Creates a {@link Selector} which selects elements by an XPath expression
     *
     * @param xPath the XPath expression
     * @return the selector
     */
    Selector byXPath(String xPath);

    /**
     * Chains multiple selectors to one. This chained selector will first select
     * all elements using the root selector and then select the elements using the
     * first child selector and so forth...
     *
     * @param selector  the root selector
     * @param selectors the child selector
     * @return the chained selector
     */
    default Selector chained(Selector selector, Selector... selectors) {
        if (selectors.length == 0) {
            return selector;
        }

        return chained(Stream.concat(
                Stream.of(selector),
                Stream.of(selectors)
        ).toList());
    }

    /**
     * Chains multiple selectors to one. This chained selector will first select
     * all elements using the first selector in the given list. Then it selects the elements using the
     * second selector and so forth...
     *
     * @param selectors the selectors
     * @return the chained selector
     * @throws IllegalArgumentException if the selectors are empty
     */
    Selector chained(List<Selector> selectors);
}
