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

        context.evaluateScript("/js/waitUntilElementIsNotOverlapped.js");
        // We cannot use getLocator().check() directly because Playwright will throw an error if the checkbox state is
        // not directly changed. This is for example the case in React applications
        if(!context.getLocator().isChecked()) {
            click();
        }
    }

    @Override
    public void uncheck() {
        var context = requirePlaywrightContext(getContext());

        context.evaluateScript("/js/waitUntilElementIsNotOverlapped.js");
        // We cannot use getLocator().uncheck() directly because Playwright will throw an error if the checkbox state is
        // not directly changed. This is for example the case in React applications
        if(context.getLocator().isChecked()) {
            click();
        }
    }
}
