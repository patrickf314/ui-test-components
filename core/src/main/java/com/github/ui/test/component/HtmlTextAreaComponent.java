package com.github.ui.test.component;

import com.github.ui.test.context.UiTestComponentContext;

public abstract class HtmlTextAreaComponent extends UiTestComponent {

    protected HtmlTextAreaComponent(UiTestComponentContext context) {
        super(context);
    }

    public abstract void setValue(String value);

}
