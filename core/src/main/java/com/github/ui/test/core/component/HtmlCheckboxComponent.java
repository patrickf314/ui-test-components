package com.github.ui.test.core.component;

import com.github.ui.test.core.context.UiTestComponentContext;

public abstract class HtmlCheckboxComponent extends UiTestComponent {

    protected HtmlCheckboxComponent(UiTestComponentContext context) {
        super(context);
    }

    public abstract void check();

    public abstract void uncheck();
}
