package com.github.ui.test.context.playwright;

import com.microsoft.playwright.Page;
import com.github.ui.test.component.UiTestComponent;
import com.github.ui.test.component.UiTestComponentList;
import com.github.ui.test.component.UiTestPage;
import com.github.ui.test.component.playwright.PlaywrightListComponent;
import com.github.ui.test.context.UiTestComponentContext;
import com.github.ui.test.context.UiTestPageContext;
import com.github.ui.test.selector.Selector;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.function.Function;

import static com.github.ui.test.selector.playwright.PlaywrightSelectors.requirePlaywrightSelector;

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
    public String getUrl() {
        return page.url();
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
        page.waitForURL(baseUrl + testPage.getPathPattern());
        return testPage;
    }

    @Override
    public <T extends UiTestPage> T reload(Function<UiTestPageContext, T> constructor) {
        page.reload();
        return waitForPage(constructor);
    }
}
