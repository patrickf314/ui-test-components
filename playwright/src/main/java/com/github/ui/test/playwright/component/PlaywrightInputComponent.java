package com.github.ui.test.playwright.component;

import com.github.ui.test.core.component.HtmlInputComponent;
import com.github.ui.test.core.context.UiTestComponentContext;

public class PlaywrightInputComponent extends HtmlInputComponent {

    public PlaywrightInputComponent(UiTestComponentContext context) {
        super(context);
    }

    @Override
    public void setValue(String value) {
        var context = PlaywrightComponentFactory.requirePlaywrightContext(getContext());

        context.getLocator().fill(value);
        context.evaluateScript("waitUntilInputLoadingFinished.js");
    }

    @Override
    public DataList getDateList() {
        return new PlaywrightDataListComponent(this);
    }
}
