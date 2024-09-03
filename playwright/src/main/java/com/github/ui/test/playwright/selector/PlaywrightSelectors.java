package com.github.ui.test.playwright.selector;

import com.github.ui.test.core.selector.UiTestSelectors;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.github.ui.test.core.exception.IllegalContextException;
import com.github.ui.test.core.selector.Selector;

import java.util.List;

public class PlaywrightSelectors implements UiTestSelectors {

    public Selector byCSSSelector(String cssSelector) {
        return new PlaywrightLocatorSelector(
                "byCSSSelector('" + cssSelector + "')",
                page -> page.locator(cssSelector),
                locator -> locator.locator(cssSelector)
        );
    }

    public Selector byText(String text) {
        return new PlaywrightLocatorSelector(
                "byText('" + text + "')",
                page -> page.getByText(text, new Page.GetByTextOptions().setExact(true)),
                locator -> locator.getByText(text, new Locator.GetByTextOptions().setExact(true))
        );
    }

    public Selector byTestId(String testId) {
        return new PlaywrightLocatorSelector(
                "byTestId('" + testId + "')",
                page -> page.getByTestId(testId),
                locator -> locator.getByTestId(testId)
        );
    }

    public Selector byXPath(String xPath) {
        return new PlaywrightLocatorSelector(
                "byXPath('" + xPath + "')",
                page -> page.locator("xpath=" + xPath),
                locator -> locator.locator("xpath=" + xPath)
        );
    }

    public Selector chained(List<Selector> selectors) {
        return new PlaywrightChainedSelector(selectors.stream()
                .map(PlaywrightSelectors::requirePlaywrightSelector)
                .toList());
    }

    public static PlaywrightSelector requirePlaywrightSelector(Selector selector) {
        if (selector instanceof PlaywrightSelector playwrightSelector) {
            return playwrightSelector;
        }

        throw new IllegalContextException("playwright", "selector of type " + selector.getClass().getName());
    }
}
