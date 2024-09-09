package com.github.ui.test.core.context;

import com.github.ui.test.core.component.UiTestComponentList;
import com.github.ui.test.core.component.UiTestPage;
import com.github.ui.test.core.component.UiTestComponent;
import com.github.ui.test.core.data.UiTestDownload;
import com.github.ui.test.core.selector.Selector;

import java.util.function.Function;

public interface UiTestElementContext {

    <T extends UiTestPage> T waitForPage(Function<UiTestPageContext, T> constructor);

    UiTestDownload waitForDownload(Runnable downloadStartAction);

    <T extends UiTestComponent> T getChild(Function<UiTestComponentContext, T> component, Selector selector);

    <T extends UiTestComponent> UiTestComponentList<T> getChildList(Function<UiTestComponentContext, T> component, Selector selector);
}
