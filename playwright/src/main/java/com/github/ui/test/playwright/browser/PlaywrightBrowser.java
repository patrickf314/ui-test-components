package com.github.ui.test.playwright.browser;

import com.github.ui.test.core.browser.UiTestBrowser;
import com.github.ui.test.core.context.UiTestContext;
import com.github.ui.test.playwright.context.PlaywrightContext;
import com.microsoft.playwright.Browser;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.Tracing;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class PlaywrightBrowser implements UiTestBrowser {

    private Playwright playwright;
    private Browser browser;

    @Override
    public void start() {
        if (playwright != null) {
            throw new IllegalStateException("Browser is already started");
        }

        playwright = Playwright.create();
        browser = playwright.firefox().launch();
    }

    @Override
    public UiTestContext createNewTestContext(String baseUrl, String outputDirectory, String testName) {
        if(browser == null) {
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
