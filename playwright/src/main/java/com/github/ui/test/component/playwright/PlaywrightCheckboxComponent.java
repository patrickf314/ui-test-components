package com.github.ui.test.component.playwright;

import com.github.ui.test.component.HtmlCheckboxComponent;
import com.github.ui.test.context.UiTestComponentContext;

import static com.github.ui.test.component.playwright.PlaywrightComponentFactory.requirePlaywrightContext;

public class PlaywrightCheckboxComponent extends HtmlCheckboxComponent {

    public PlaywrightCheckboxComponent(UiTestComponentContext context) {
        super(context);
    }

    @Override
    public void check() {
        requirePlaywrightContext(getContext()).getLocator().check();
    }

    @Override
    public void uncheck() {
        requirePlaywrightContext(getContext()).getLocator().uncheck();
    }
}
