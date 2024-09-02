package com.github.ui.test.browser.playwright;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.Tracing;
import com.github.ui.test.browser.UiTestBrowser;
import com.github.ui.test.context.UiTestContext;
import com.github.ui.test.context.playwright.PlaywrightContext;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;

import java.util.UUID;

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

        var traceId = UUID.randomUUID().toString();
        MDC.put("traceId", traceId);

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
