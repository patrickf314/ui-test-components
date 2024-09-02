package com.github.ui.test.component;

import com.github.ui.test.context.UiTestComponentContext;

public abstract class HtmlCheckboxComponent extends UiTestComponent {

    protected HtmlCheckboxComponent(UiTestComponentContext context) {
        super(context);
    }

    public abstract void check();

    public abstract void uncheck();
}
