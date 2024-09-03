package com.github.ui.test.core.selector;

import java.util.List;
import java.util.stream.Stream;

public interface UiTestSelectors {

    Selector byCSSSelector(String cssSelector);

    Selector byText(String text);

    Selector byTestId(String testId);

    Selector byXPath(String xPath);

    default Selector chained(Selector selector, Selector... selectors) {
        if (selectors.length == 0) {
            return selector;
        }

        return chained(Stream.concat(
                Stream.of(selector),
                Stream.of(selectors)
        ).toList());
    }

    Selector chained(List<Selector> selectors);
}
