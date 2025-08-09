package com.github.ui.test.core.component;

import com.github.ui.test.core.context.UiTestComponentContext;

/**
 * {@link UiTestComponent} implementation for an HTML image {@code <img>}
 *
 * @see UiTestComponent#imageComponent() ()
 */
public class HtmlImageComponent extends UiTestComponent {

    /**
     * Constructor
     *
     * @param context the component context
     */
    protected HtmlImageComponent(UiTestComponentContext context) {
        super(context);
    }
}
