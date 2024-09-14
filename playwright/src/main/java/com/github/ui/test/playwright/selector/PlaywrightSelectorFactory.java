package com.github.ui.test.playwright.selector;

import com.github.ui.test.core.exception.IllegalContextException;
import com.github.ui.test.core.selector.Selector;
import com.github.ui.test.core.selector.SelectorFactory;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

import java.util.List;

/**
 * Playwright implementation of a {@link SelectorFactory}
 */
public class PlaywrightSelectorFactory implements SelectorFactory {

    @Override
    public Selector byCSSSelector(String cssSelector) {
        return new PlaywrightLocatorSelector(
                "byCSSSelector('" + cssSelector + "')",
                page -> page.locator(cssSelector),
                locator -> locator.locator(cssSelector)
        );
    }

    @Override
    public Selector byText(String text) {
        return new PlaywrightLocatorSelector(
                "byText('" + text + "')",
                page -> page.getByText(text, new Page.GetByTextOptions().setExact(true)),
                locator -> locator.getByText(text, new Locator.GetByTextOptions().setExact(true))
        );
    }

    @Override
    public Selector byTestId(String testId) {
        return new PlaywrightLocatorSelector(
                "byTestId('" + testId + "')",
                page -> page.getByTestId(testId),
                locator -> locator.getByTestId(testId)
        );
    }

    @Override
    public Selector byXPath(String xPath) {
        return new PlaywrightLocatorSelector(
                "byXPath('" + xPath + "')",
                page -> page.locator("xpath=" + xPath),
                locator -> locator.locator("xpath=" + xPath)
        );
    }

    @Override
    public Selector chained(List<Selector> selectors) {
        if (selectors.isEmpty()) {
            throw new IllegalArgumentException("selectors must not be empty");
        }

        if (selectors.size() == 1) {
            return selectors.get(0);
        }

        return new PlaywrightChainedSelector(selectors.stream()
                .map(PlaywrightSelectorFactory::requirePlaywrightSelector)
                .toList());
    }

    /**
     * Requires that the given selector is a {@link PlaywrightSelector}.
     *
     * @param selector the selector to check
     * @return the given selector
     * @throws IllegalContextException if the selector is not a {@link PlaywrightSelector}
     */
    public static PlaywrightSelector requirePlaywrightSelector(Selector selector) {
        if (selector instanceof PlaywrightSelector playwrightSelector) {
            return playwrightSelector;
        }

        throw new IllegalContextException("playwright", "selector of type " + selector.getClass().getName());
    }
}
