package com.github.ui.test.component.playwright;

import com.github.ui.test.component.HtmlTextAreaComponent;
import com.github.ui.test.context.UiTestComponentContext;

import static com.github.ui.test.component.playwright.PlaywrightComponentFactory.requirePlaywrightContext;

public class PlaywrightTextAreaComponent extends HtmlTextAreaComponent {

    public PlaywrightTextAreaComponent(UiTestComponentContext context) {
        super(context);
    }

    @Override
    public void setValue(String value) {
        requirePlaywrightContext(getContext()).getLocator().fill(value);
    }
}
