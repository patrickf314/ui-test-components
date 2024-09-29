package com.github.ui.test.playwright.browser;

import com.github.ui.test.core.browser.UiTestBrowser;
import com.github.ui.test.core.browser.UiTestBrowserType;
import com.github.ui.test.playwright.PlaywrightUiTestEnvironment;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Playwright;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.function.Function;

@Slf4j
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public enum PlaywrightBrowserType implements UiTestBrowserType {

    CHROMIUM(Playwright::chromium),
    FIREFOX(Playwright::firefox),
    WEBKIT(Playwright::webkit);

    private final Function<Playwright, BrowserType> browserType;

    @Override
    public UiTestBrowser startBrowser() {
        var environment = PlaywrightUiTestEnvironment.getEnvironment();
        var options = environment.getOptions();
        var playwright = environment.getPlaywright();

        playwright.selectors().setTestIdAttribute(options.getTestIdAttribute());

        var browser = browserType.apply(playwright).launch();
        return new PlaywrightBrowser(options.getTimeout(), browser);
    }
}
