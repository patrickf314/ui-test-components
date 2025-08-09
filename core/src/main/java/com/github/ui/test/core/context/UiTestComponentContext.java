package com.github.ui.test.core.context;

import com.github.ui.test.core.action.UiTestCursorAction;
import com.github.ui.test.core.action.UiTestScrollAction;
import com.github.ui.test.core.selector.Selector;

public interface UiTestComponentContext extends UiTestElementContext {

    default void hover(Selector... selectors) {
        hover(false, selectors);
    }

    void hover(boolean skipIfInvisible, Selector... selectors);

    void click(Selector... selectors);

    /**
     * Sends the given text to the component.
     * This function simulates user behavior.
     * Thus, the typing corresponds to the actual behavior of a user interacting with
     * the DOM.
     * Hence, typing might be slower than {@link com.github.ui.test.core.component.HtmlInputComponent#setValue(String)}
     * and this function should not be used to write long texts.
     *
     * @param text
     * @param selectors
     */
    void sendKeys(String text, Selector... selectors);

    /**
     * Getter of a {@link UiTestCursorAction} starting
     * in the top left corner of this component.
     *
     * @return the mouse action
     */
    UiTestCursorAction cursor();

    /**
     * Getter of a {@link UiTestScrollAction} in this component.
     *
     *
     * @return the scroll action
     */
    UiTestScrollAction scroll();
}
