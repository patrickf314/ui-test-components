package com.github.ui.test.context.playwright;

import com.microsoft.playwright.Locator;
import com.github.ui.test.component.UiTestComponent;
import com.github.ui.test.component.UiTestPage;
import com.github.ui.test.component.playwright.PlaywrightListComponent;
import com.github.ui.test.context.UiTestComponentContext;
import com.github.ui.test.context.UiTestPageContext;
import com.github.ui.test.predicate.UiTestComponentPredicate;
import com.github.ui.test.selector.Selector;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.function.Function;

import static com.github.ui.test.predicate.playwright.PlaywrightComponentPredicates.requirePlaywrightPredicate;
import static com.github.ui.test.selector.playwright.PlaywrightSelectors.requirePlaywrightSelector;

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
    public <T extends UiTestComponent> T getChild(Function<UiTestComponentContext, T> component, Selector selector) {
        return component.apply(new PlaywrightComponentContext(pageContext, requirePlaywrightSelector(selector).asChildLocatorOf(locator)));
    }

    @Override
    public <T extends UiTestComponent> PlaywrightListComponent<T> getChildList(Function<UiTestComponentContext, T> component, Selector selector) {
        return new PlaywrightListComponent<>(
                new PlaywrightComponentContext(pageContext, requirePlaywrightSelector(selector).asChildLocatorOf(locator)),
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
        if(!childLocator.isVisible() && skipIfInvisible) {
            return;
        }
        childLocator.hover();
    }

    @Override
    public void sendKeys(String text, Selector... selectors) {
        select(selectors).pressSequentially(text);
    }

    public <T extends UiTestComponent> T nth(Function<UiTestComponentContext, T> component, int index) {
        return component.apply(new PlaywrightComponentContext(pageContext, locator.nth(index)));
    }

    public <T extends UiTestComponent> PlaywrightListComponent<T> filter(Function<UiTestComponentContext, T> itemConstructor, UiTestComponentPredicate predicate) {
        return new PlaywrightListComponent<>(
                new PlaywrightComponentContext(pageContext, requirePlaywrightPredicate(predicate).filter(pageContext.getPage(), locator)),
                itemConstructor
        );
    }

    public Object evaluateScript(String script) {
        return evaluateScript(script, null);
    }

    public Object evaluateScript(String script, Object arg) {
        try (var in = this.getClass().getResourceAsStream(script)) {
            if (in == null) {
                throw new FileNotFoundException("Script '" + script + "' not found");
            }

            var expression = new String(in.readAllBytes(), StandardCharsets.UTF_8);
            return locator.evaluate(expression, arg);
        } catch (IOException e) {
            throw new IllegalStateException("Failed to load script " + script, e);
        }
    }

    private Locator select(Selector... selectors) {
        var childSelector = locator;
        for(var selector : selectors) {
            childSelector = requirePlaywrightSelector(selector).asChildLocatorOf(childSelector);
        }
        return childSelector;
    }
}
