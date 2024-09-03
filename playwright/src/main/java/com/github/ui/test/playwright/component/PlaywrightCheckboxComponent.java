package com.github.ui.test.playwright.component;

import com.github.ui.test.core.component.HtmlCheckboxComponent;
import com.github.ui.test.core.context.UiTestComponentContext;

public class PlaywrightCheckboxComponent extends HtmlCheckboxComponent {

    public PlaywrightCheckboxComponent(UiTestComponentContext context) {
        super(context);
    }

    @Override
    public void check() {
        PlaywrightComponentFactory.requirePlaywrightContext(getContext()).getLocator().check();
    }

    @Override
    public void uncheck() {
        PlaywrightComponentFactory.requirePlaywrightContext(getContext()).getLocator().uncheck();
    }
}
