package com.github.ui.test.context;

import com.github.ui.test.component.UiTestComponent;
import com.github.ui.test.component.UiTestComponentList;
import com.github.ui.test.component.UiTestPage;
import com.github.ui.test.selector.Selector;

import java.util.function.Function;

public interface UiTestElementContext {

    <T extends UiTestPage> T waitForPage(Function<UiTestPageContext, T> constructor);

    <T extends UiTestComponent> T getChild(Function<UiTestComponentContext, T> component, Selector selector);

    <T extends UiTestComponent> UiTestComponentList<T> getChildList(Function<UiTestComponentContext, T> component, Selector selector);
}
