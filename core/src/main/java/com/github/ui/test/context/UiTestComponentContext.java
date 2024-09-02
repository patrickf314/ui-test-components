package com.github.ui.test.context;

import com.github.ui.test.selector.Selector;

public interface UiTestComponentContext extends UiTestElementContext {

    default void hover(Selector... selectors) {
        hover(false, selectors);
    }

    void hover(boolean skipIfInvisible, Selector... selectors);

    void click(Selector... selectors);

    void sendKeys(String text, Selector... selectors);
}
