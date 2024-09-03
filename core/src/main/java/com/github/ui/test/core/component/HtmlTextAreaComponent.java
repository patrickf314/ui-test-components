package com.github.ui.test.core.component;

import com.github.ui.test.core.context.UiTestComponentContext;

public abstract class HtmlTextAreaComponent extends UiTestComponent {

    protected HtmlTextAreaComponent(UiTestComponentContext context) {
        super(context);
    }

    public abstract void setValue(String value);

}
