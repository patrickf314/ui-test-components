package com.github.ui.test.playwright;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

import java.time.Duration;

public class PlaywrightTestUtils {

    private static final Duration TIMEOUT = Duration.ofSeconds(1);

    public static PlaywrightTestContext mockPage(String content) {
        var playwright = Playwright.create();
        var browser = playwright.firefox().launch();
        var page = browser.newPage();

        page.setDefaultTimeout(TIMEOUT.toMillis());
        page.setContent(content);

        return new PlaywrightTestContext(playwright, browser, page);
    }

    public record PlaywrightTestContext(Playwright playwright, Browser browser, Page page) implements AutoCloseable {
        @Override
        public void close() {
            page.close();
            browser.close();
            playwright.close();
        }
    }
}
