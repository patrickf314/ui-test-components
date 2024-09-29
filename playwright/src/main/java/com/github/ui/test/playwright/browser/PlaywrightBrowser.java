package com.github.ui.test.playwright.browser;

import com.github.ui.test.core.browser.UiTestBrowser;
import com.github.ui.test.core.context.UiTestContext;
import com.github.ui.test.playwright.context.PlaywrightContext;
import com.microsoft.playwright.Browser;
import com.microsoft.playwright.Tracing;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.time.Duration;

@Slf4j
@RequiredArgsConstructor
public class PlaywrightBrowser implements UiTestBrowser {

    private final Duration defaultTimeout;
    private final Browser browser;

    @Override
    public UiTestContext createNewTestContext(String baseUrl, String outputDirectory, String testName) {
        var browserContext = browser.newContext();
        browserContext.tracing().start(
                new Tracing.StartOptions()
                        .setScreenshots(true)
                        .setSnapshots(true)
                        .setSources(true)
        );


        log.info("Created test context for {}", testName);
        return new PlaywrightContext(defaultTimeout, baseUrl, outputDirectory, testName, browserContext);
    }

    @Override
    public void stop() {
        browser.close();
    }
}
