package com.github.ui.test.playwright.component;

import com.github.ui.test.core.component.HtmlCheckboxComponent;
import com.github.ui.test.core.context.UiTestComponentContext;

import static com.github.ui.test.playwright.component.PlaywrightComponentFactory.requirePlaywrightContext;

public class PlaywrightCheckboxComponent extends HtmlCheckboxComponent {

    public PlaywrightCheckboxComponent(UiTestComponentContext context) {
        super(context);
    }

    @Override
    public void check() {
        var context = requirePlaywrightContext(getContext());

        context.evaluateScript("/js/waitInputEditable.js");
        context.getLocator().check();
    }

    @Override
    public void uncheck() {
        var context = requirePlaywrightContext(getContext());

        context.evaluateScript("/js/waitInputEditable.js");
        context.getLocator().uncheck();
    }
}
