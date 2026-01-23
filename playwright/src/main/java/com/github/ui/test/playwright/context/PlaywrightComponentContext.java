package com.github.ui.test.playwright.context;

import com.github.ui.test.core.action.UiTestCursorAction;
import com.github.ui.test.core.action.UiTestScrollAction;
import com.github.ui.test.core.component.UiTestComponent;
import com.github.ui.test.core.component.UiTestPage;
import com.github.ui.test.core.context.UiTestComponentContext;
import com.github.ui.test.core.context.UiTestPageContext;
import com.github.ui.test.core.data.UiTestDownload;
import com.github.ui.test.core.predicate.UiTestComponentPredicate;
import com.github.ui.test.core.selector.Selector;
import com.github.ui.test.playwright.action.PlaywrightCursorAction;
import com.github.ui.test.playwright.action.PlaywrightScrollAction;
import com.github.ui.test.playwright.component.PlaywrightListComponent;
import com.github.ui.test.playwright.predicate.PlaywrightComponentPredicateFactory;
import com.github.ui.test.playwright.selector.PlaywrightSelectorFactory;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.options.WaitForSelectorState;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.function.Function;

@Getter
@RequiredArgsConstructor
public class PlaywrightComponentContext implements UiTestComponentContext {

    private final PlaywrightPageContext pageContext;
    private final Locator locator;

    @Override
    public <T extends UiTestPage> T waitForPage(Function<UiTestPageContext, T> constructor) {
        return pageContext.waitForPage(constructor);
    }

    @Override
    public UiTestDownload waitForDownload(Runnable downloadStartAction) {
        return pageContext.waitForDownload(downloadStartAction);
    }

    @Override
    public <T extends UiTestComponent> T getChild(Function<UiTestComponentContext, T> component, Selector selector) {
        return component.apply(new PlaywrightComponentContext(pageContext, PlaywrightSelectorFactory.requirePlaywrightSelector(selector).asChildLocatorOf(locator)));
    }

    @Override
    public <T extends UiTestComponent> PlaywrightListComponent<T> getChildList(Function<UiTestComponentContext, T> component, Selector selector) {
        return new PlaywrightListComponent<>(
                new PlaywrightComponentContext(pageContext, PlaywrightSelectorFactory.requirePlaywrightSelector(selector).asChildLocatorOf(locator)),
                component
        );
    }

    @Override
    public void click(Selector... selectors) {
        select(selectors).click();
    }

    @Override
    public void hover(boolean skipIfInvisible, Selector... selectors) {
        var childLocator = select(selectors);
        if (!childLocator.isVisible() && skipIfInvisible) {
            return;
        }
        childLocator.hover();
    }

    @Override
    public void sendKeys(String text, Selector... selectors) {
        select(selectors).pressSequentially(text, new Locator.PressSequentiallyOptions().setDelay(50));
    }

    @Override
    public UiTestCursorAction cursor() {
        return new PlaywrightCursorAction(this);
    }

    @Override
    public UiTestScrollAction scroll() {
        return new PlaywrightScrollAction(this);
    }

    public <T extends UiTestComponent> T nth(Function<UiTestComponentContext, T> component, int index) {
        return component.apply(new PlaywrightComponentContext(pageContext, locator.nth(index)));
    }

    public <T extends UiTestComponent> PlaywrightListComponent<T> filter(Function<UiTestComponentContext, T> itemConstructor, UiTestComponentPredicate predicate) {
        return new PlaywrightListComponent<>(
                new PlaywrightComponentContext(pageContext, PlaywrightComponentPredicateFactory.requirePlaywrightPredicate(predicate).filter(pageContext.getPage(), locator)),
                itemConstructor
        );
    }

    public Object evaluateScript(String script) {
        return evaluateScript(script, null);
    }

    public Object evaluateScript(String script, Object parameter) {
        try (var in = this.getClass().getResourceAsStream(script)) {
            if (in == null) {
                throw new FileNotFoundException("Script '" + script + "' not found");
            }

            var expression = new String(in.readAllBytes(), StandardCharsets.UTF_8);
            var arg = new HashMap<String, Object>();

            arg.put("timeout", 10 /* seconds */);
            if (parameter != null) {
                arg.put("parameter", parameter);
            }

            locator.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.ATTACHED));
            return locator.evaluate(expression, arg);
        } catch (IOException e) {
            throw new IllegalStateException("Failed to load script " + script, e);
        }
    }

    private Locator select(Selector... selectors) {
        var childSelector = locator;
        for (var selector : selectors) {
            childSelector = PlaywrightSelectorFactory.requirePlaywrightSelector(selector).asChildLocatorOf(childSelector);
        }
        return childSelector;
    }
}
