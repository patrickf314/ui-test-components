package com.github.ui.test.core.component;

import com.github.ui.test.core.context.UiTestComponentContext;

/**
 * {@link UiTestComponent} implementation for an HTML checkbox {@code <input type="checkbox">}
 *
 * @see UiTestComponent#checkboxComponent()
 */
public abstract class HtmlCheckboxComponent extends UiTestComponent {

    /**
     * Constructor.
     * Use {@link UiTestComponent#checkboxComponent()} to create this component.
     *
     * @param context the component context
     */
    protected HtmlCheckboxComponent(UiTestComponentContext context) {
        super(context);
    }

    /**
     * Ensures that the checkbox is checked.
     */
    public abstract void check();

    /**
     * Ensures that the checkbox is not checked.
     */
    public abstract void uncheck();
}
