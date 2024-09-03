package com.github.ui.test.playwright;

import com.github.ui.test.core.UiTestEnvironment;
import com.github.ui.test.playwright.browser.PlaywrightBrowser;
import com.github.ui.test.playwright.component.PlaywrightComponentFactory;
import com.github.ui.test.playwright.predicate.PlaywrightComponentPredicates;
import com.github.ui.test.playwright.selector.PlaywrightSelectors;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class PlaywrightUiTestEnvironment extends UiTestEnvironment {

    private final PlaywrightBrowser browser;
    private final PlaywrightComponentFactory componentFactory = new PlaywrightComponentFactory();
    private final PlaywrightComponentPredicates predicates = new PlaywrightComponentPredicates();
    private final PlaywrightSelectors selectors = new PlaywrightSelectors();

}
