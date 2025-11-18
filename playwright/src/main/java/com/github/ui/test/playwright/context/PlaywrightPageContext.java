package com.github.ui.test.playwright.context;

import com.github.ui.test.core.component.UiTestComponent;
import com.github.ui.test.core.component.UiTestComponentList;
import com.github.ui.test.core.component.UiTestPage;
import com.github.ui.test.core.context.UiTestComponentContext;
import com.github.ui.test.core.context.UiTestPageContext;
import com.github.ui.test.core.data.UiTestDownload;
import com.github.ui.test.core.selector.Selector;
import com.github.ui.test.playwright.component.PlaywrightListComponent;
import com.github.ui.test.playwright.data.PlaywrightDownload;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.PlaywrightException;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;

import static com.github.ui.test.playwright.selector.PlaywrightSelectorFactory.requirePlaywrightSelector;

@Slf4j
@Getter
@RequiredArgsConstructor
public class PlaywrightPageContext implements UiTestPageContext {

    private final String baseUrl;
    private final Page page;

    @Override
    public void close() {
        page.close();
    }

    @Override
    public Url getUrl() {
        return parseLocation(page.url()).orElseThrow(() -> new IllegalStateException("Failed to parse current url"));
    }

    @Override
    public <T extends UiTestComponent> T getChild(Function<UiTestComponentContext, T> component, Selector selector) {
        return component.apply(new PlaywrightComponentContext(this, requirePlaywrightSelector(selector).locateOn(page)));
    }

    @Override
    public <T extends UiTestComponent> UiTestComponentList<T> getChildList(Function<UiTestComponentContext, T> component, Selector selector) {
        return new PlaywrightListComponent<>(
                new PlaywrightComponentContext(this, requirePlaywrightSelector(selector).locateOn(page)),
                component
        );
    }

    @Override
    public <T extends UiTestPage> T waitForPage(Function<UiTestPageContext, T> constructor) {
        var testPage = constructor.apply(this);

        try {
            page.waitForURL(url -> parseLocation(url)
                    .filter(testPage::matchesUrl)
                    .isPresent());
        } catch (PlaywrightException e) {
            throw new IllegalStateException("Failed to wait for page, url was " + page.url() + " which did not match the path of " + testPage.getClass().getName() + ".", e);
        }

        return testPage;
    }

    @Override
    public UiTestDownload waitForDownload(Runnable downloadStartAction) {
        var download = page.waitForDownload(downloadStartAction);
        return new PlaywrightDownload(download);
    }

    @Override
    public <T extends UiTestPage> T reload(Function<UiTestPageContext, T> constructor) {
        page.reload();
        return waitForPage(constructor);
    }

    @Override
    public <T extends UiTestPage> T navigateTo(Function<UiTestPageContext, T> constructor, String path) {
        page.navigate(baseUrl + path);
        return waitForPage(constructor);
    }

    private Optional<Url> parseLocation(String url) {
        if (url.length() < baseUrl.length()) {
            return Optional.empty();
        }

        var baseUrl = url.substring(0, this.baseUrl.length());
        if (!baseUrl.equalsIgnoreCase(this.baseUrl)) {
            return Optional.empty();
        }

        var path = url.substring(this.baseUrl.length());

        var i = path.indexOf('?');
        var queryParameters = Collections.<String, String>emptyMap();
        if (i != -1) {
            queryParameters = parseQueryParameters(path.substring(i + 1));
            path = path.substring(0, i);
        }

        var j = path.indexOf('#');
        String anchor = null;
        if (j != -1) {
            anchor = anchor.substring(j + 1);
            path = path.substring(0, j);
        }

        return Optional.of(new Url(
                baseUrl, path, queryParameters, anchor
        ));
    }

    private static Map<String, String> parseQueryParameters(String query) {
        var parameters = new HashMap<String, String>();
        var entries = query.split("&");
        for (var entry : entries) {
            var i = entry.indexOf("=");
            var name = URLDecoder.decode(i == -1 ? entry : entry.substring(0, i), StandardCharsets.UTF_8);
            var value = i == -1 ? "true" : URLDecoder.decode(entry.substring(i + 1), StandardCharsets.UTF_8);
            parameters.put(name, value);
        }
        return parameters;
    }
}
