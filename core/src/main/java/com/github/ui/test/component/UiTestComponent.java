package com.github.ui.test.component;

import com.github.ui.test.context.UiTestComponentContext;

public class UiTestComponent extends UiTestElement<UiTestComponentContext> {

    public UiTestComponent(UiTestComponentContext context) {
        super(context);
    }

    public void click() {
        getContext().click();
    }
}
