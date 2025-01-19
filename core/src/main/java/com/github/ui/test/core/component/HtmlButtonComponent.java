package com.github.ui.test.core.component;

import com.github.ui.test.core.context.UiTestComponentContext;

/**
 * {@link UiTestComponent} implementation for a HTML button {@code <button>}
 *
 * @see UiTestComponent#buttonComponent() ()
 */
public class HtmlButtonComponent extends UiTestComponent {

    /**
     * Constructor.
     * Use {@link UiTestComponent#buttonComponent()} ()} to create this component.
     *
     * @param context the component context
     */
    protected HtmlButtonComponent(UiTestComponentContext context) {
        super(context);
    }
}
