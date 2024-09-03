package com.github.ui.test.playwright.component;

import com.github.ui.test.core.component.HtmlTextAreaComponent;
import com.github.ui.test.core.context.UiTestComponentContext;

public class PlaywrightTextAreaComponent extends HtmlTextAreaComponent {

    public PlaywrightTextAreaComponent(UiTestComponentContext context) {
        super(context);
    }

    @Override
    public void setValue(String value) {
        PlaywrightComponentFactory.requirePlaywrightContext(getContext()).getLocator().fill(value);
    }
}
