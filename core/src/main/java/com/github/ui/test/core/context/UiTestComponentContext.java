package com.github.ui.test.core.context;

import com.github.ui.test.core.selector.Selector;

public interface UiTestComponentContext extends UiTestElementContext {

    default void hover(Selector... selectors) {
        hover(false, selectors);
    }

    void hover(boolean skipIfInvisible, Selector... selectors);

    void click(Selector... selectors);

    /**
     * Sends the given text to the component.
     * This function simulates user behaviour.
     * Thus, the typing corresponds to the actual behaviour of a user interacting with
     * the DOM.
     * Hence, typing might be slower than {@link com.github.ui.test.core.component.HtmlInputComponent#setValue(String)}
     * and this function should not be used to write long texts.
     *
     * @param text
     * @param selectors
     */
    void sendKeys(String text, Selector... selectors);
}
