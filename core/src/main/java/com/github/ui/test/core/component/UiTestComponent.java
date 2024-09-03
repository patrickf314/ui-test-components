package com.github.ui.test.core.component;

import com.github.ui.test.core.context.UiTestComponentContext;

public class UiTestComponent extends UiTestElement<UiTestComponentContext> {

    public UiTestComponent(UiTestComponentContext context) {
        super(context);
    }

    public void click() {
        getContext().click();
    }
}
