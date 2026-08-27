package com.github.ui.test.core.component;

import com.github.ui.test.core.context.UiTestComponentContext;

/**
 * {@link UiTestComponent} implementation for an HTML element, which when clicked is
 * expected to copy some text to the clipboard. The copied text can be retrieved by calling
 *
 * @see UiTestComponent#copyToClipboardComponent()}
 */
public abstract class CopyToClipboardComponent extends UiTestComponent {

    /**
     * Constructor
     *
     * @param context the component context
     */
    protected CopyToClipboardComponent(UiTestComponentContext context) {
        super(context);
    }

    /**
     * Clicks the component and returns the copied text
     *
     * @return the copied text
     */
    public abstract String clickAndGetCopiedText();

}
