package com.github.ui.test.core.component;

import com.github.ui.test.core.context.UiTestComponentContext;
import com.github.ui.test.core.context.UiTestElementContext;
import com.github.ui.test.core.context.UiTestPageContext;
import com.github.ui.test.core.selector.Selector;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.function.Function;

import static com.github.ui.test.core.UiTestEnvironment.chained;

@Getter
@RequiredArgsConstructor
public class UiTestElement<C extends UiTestElementContext> {

    private final C context;

    protected <T extends UiTestPage> T waitForPage(Function<UiTestPageContext, T> constructor) {
        return context.waitForPage(constructor);
    }

    protected UiTestComponent getChild(Selector selector, Selector... selectors) {
        return getChild(UiTestComponent::new, selector, selectors);
    }

    protected <T extends UiTestComponent> T getChild(Function<UiTestComponentContext, T> component, Selector selector, Selector... selectors) {
        return getChild(component, chained(selector, selectors));
    }

    protected <T extends UiTestComponent> T getChild(Function<UiTestComponentContext, T> component, Selector selector) {
        return context.getChild(component, selector);
    }

    protected UiTestComponentList<UiTestComponent> getChildList(Selector selector) {
        return getChildList(UiTestComponent::new, selector);
    }

    protected <T extends UiTestComponent> UiTestComponentList<T> getChildList(Function<UiTestComponentContext, T> component, Selector selector, Selector... selectors) {
        return getChildList(component, chained(selector, selectors));
    }

    protected <T extends UiTestComponent> UiTestComponentList<T> getChildList(Function<UiTestComponentContext, T> component, Selector selector) {
        return context.getChildList(component, selector);
    }
}
