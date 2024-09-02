package com.github.ui.test.selector;

import com.github.ui.test.selector.playwright.PlaywrightSelectors;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

import java.util.stream.Stream;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public final class Selectors {

    private static final PlaywrightSelectors factor = new PlaywrightSelectors();

    public static Selector byCSSSelector(String cssSelector) {
        return factor.byCSSSelector(cssSelector);
    }

    public static Selector byText(String text) {
        return factor.byText(text);
    }

    public static Selector byTestId(String testId) {
        return factor.byTestId(testId);
    }

    public static Selector byXPath(String xPath) {
        return factor.byXPath(xPath);
    }

    public static Selector chained(Selector selector, Selector... selectors) {
        if(selectors.length == 0) {
            return selector;
        }

        return factor.chained(Stream.concat(
                Stream.of(selector),
                Stream.of(selectors)
        ).toList());
    }
}
