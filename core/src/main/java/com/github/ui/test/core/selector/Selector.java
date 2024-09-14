package com.github.ui.test.core.selector;

import static com.github.ui.test.core.UiTestEnvironment.getEnvironment;

/**
 * A selector represents an expression, which can
 * be used to select elements on an HTML site.
 */
public interface Selector {

    /**
     * Creates a {@link Selector} from a CSS selector
     *
     * @param cssSelector the CSS selector
     * @return the selector
     */
    static Selector byCSSSelector(String cssSelector) {
        return getEnvironment().getSelectorFactory().byCSSSelector(cssSelector);
    }

    /**
     * Creates a {@link Selector} which selects elements by its text content.
     *
     * @param text the text content
     * @return the selector
     */
    static Selector byText(String text) {
        return getEnvironment().getSelectorFactory().byText(text);
    }

    /**
     * Creates a {@link Selector} which selects elements by the test id attribute.
     *
     * @param testId the test id
     * @return the selector
     */
    static Selector byTestId(String testId) {
        return getEnvironment().getSelectorFactory().byTestId(testId);
    }

    /**
     * Creates a {@link Selector} which selects elements by an XPath expression
     *
     * @param xPath the XPath expression
     * @return the selector
     */
    static Selector byXPath(String xPath) {
        return getEnvironment().getSelectorFactory().byXPath(xPath);
    }

    /**
     * Chains multiple selectors to one. This chained selector will first select
     * all elements using the root selector and then select the elements using the
     * first child selector and so forth...
     *
     * @param selector  the root selector
     * @param selectors the child selector
     * @return the chained selector
     */
    static Selector chained(Selector selector, Selector... selectors) {
        return getEnvironment().getSelectorFactory().chained(selector, selectors);
    }

    /**
     * Restricts this selector the nth element,
     * which is selected by this.
     *
     * @param n the index of the select element (0 based)
     * @return the restricted selector
     * @throws IndexOutOfBoundsException if this selector does not select at least n elements or if n is negative
     */
    Selector nth(int n);

}
