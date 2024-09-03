package com.github.ui.test.core.context;

import com.github.ui.test.core.selector.Selector;

public interface UiTestComponentContext extends UiTestElementContext {

    default void hover(Selector... selectors) {
        hover(false, selectors);
    }

    void hover(boolean skipIfInvisible, Selector... selectors);

    void click(Selector... selectors);

    void sendKeys(String text, Selector... selectors);
}
