package com.github.ui.test.playwright.component;

import com.github.ui.test.core.component.HtmlTextAreaComponent;
import com.github.ui.test.core.context.UiTestComponentContext;

import static com.github.ui.test.playwright.component.PlaywrightComponentFactory.requirePlaywrightContext;

public class PlaywrightTextAreaComponent extends HtmlTextAreaComponent {

    public PlaywrightTextAreaComponent(UiTestComponentContext context) {
        super(context);
    }

    @Override
    public void setValue(String value) {
        var context = requirePlaywrightContext(getContext());

        context.evaluateScript("/js/waitUntilElementIsNotOverlapped.js");
        context.getLocator().fill(value);
    }
}
