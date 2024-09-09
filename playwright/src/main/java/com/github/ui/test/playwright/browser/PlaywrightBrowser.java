package com.github.ui.test.playwright.browser;

import com.github.ui.test.core.browser.UiTestBrowser;
import com.github.ui.test.core.context.UiTestContext;
import com.github.ui.test.playwright.context.PlaywrightContext;
import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.Tracing;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.function.Function;

@Slf4j
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public enum PlaywrightBrowser implements UiTestBrowser {

    CHROMIUM(Playwright::chromium),
    FIREFOX(Playwright::firefox),
    WEBKIT(Playwright::webkit);

    private final Function<Playwright, BrowserType> browserType;

    private Playwright playwright;
    private Browser browser;

    @Override
    public void start() {
        if (playwright != null) {
            throw new IllegalStateException("Browser is already started");
        }

        playwright = Playwright.create();
        browser = browserType.apply(playwright).launch();
    }

    @Override
    public UiTestContext createNewTestContext(String baseUrl, String outputDirectory, String testName) {
        if (browser == null) {
            throw new IllegalStateException("Browser is not started");
        }

        var browserContext = browser.newContext();
        browserContext.tracing().start(
                new Tracing.StartOptions()
                        .setScreenshots(true)
                        .setSnapshots(true)
                        .setSources(true)
        );


        log.info("Created test context for {}", testName);
        return new PlaywrightContext(baseUrl, outputDirectory, testName, browserContext);
    }

    @Override
    public void stop() {
        playwright.close();
    }
}
