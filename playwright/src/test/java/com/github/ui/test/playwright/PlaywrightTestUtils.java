package com.github.ui.test.playwright;

import com.github.ui.test.common.io.IOUtils;
import com.github.ui.test.core.component.UiTestComponent;
import com.github.ui.test.core.context.UiTestComponentContext;
import com.github.ui.test.playwright.context.PlaywrightComponentContext;
import com.github.ui.test.playwright.context.PlaywrightPageContext;
import com.microsoft.playwright.Browser;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.assertions.PlaywrightAssertions;

import java.io.IOException;
import java.io.InputStream;
import java.time.Duration;
import java.util.function.Function;

public class PlaywrightTestUtils {

    private static final Duration TIMEOUT = Duration.ofSeconds(1);

    public static PlaywrightTestContext mockPage(InputStream content) {
        if (content == null) {
            throw new IllegalArgumentException("Content must not be null");
        }

        try {
            return mockPage(IOUtils.toString(content));
        } catch (IOException e) {
            throw new IllegalStateException("Failed to read content", e);
        }
    }

    public static PlaywrightTestContext mockPage(String content) {
        PlaywrightAssertions.setDefaultAssertionTimeout(TIMEOUT.toMillis());

        var playwright = Playwright.create();
        var browser = playwright.firefox().launch();
        var page = browser.newPage();

        page.setDefaultTimeout(TIMEOUT.toMillis());
        page.setContent(content, new Page.SetContentOptions()
                .setTimeout(Duration.ofSeconds(10).toMillis()));

        return new PlaywrightTestContext(playwright, browser, page);
    }

    public record PlaywrightTestContext(Playwright playwright, Browser browser, Page page) implements AutoCloseable {

        private static final String DEFAULT_BASE_URL = "http://localhost";

        public Locator locator(String selector) {
            return page.locator(selector);
        }

        public PlaywrightPageContext pageContext() {
            return pageContext(DEFAULT_BASE_URL);
        }

        public PlaywrightPageContext pageContext(String baseUrl) {
            return new PlaywrightPageContext(baseUrl, page);
        }

        public PlaywrightComponentContext componentContext(String selector) {
            return componentContext(pageContext(), selector);
        }

        public PlaywrightComponentContext componentContext(PlaywrightPageContext pageContext, String selector) {
            return new PlaywrightComponentContext(pageContext, locator(selector));
        }

        public UiTestComponent component(String selector) {
            return component(selector, UiTestComponent::new);
        }

        public <T extends UiTestComponent> T component(String selector, Function<UiTestComponentContext, T> componentConstructor) {
            return componentConstructor.apply(componentContext(selector));
        }

        @Override
        public void close() {
            page.close();
            browser.close();
            playwright.close();
        }
    }
}
