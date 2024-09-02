package com.github.ui.test.component.playwright;

import com.github.ui.test.component.HtmlInputComponent;
import com.github.ui.test.context.UiTestComponentContext;

import static com.github.ui.test.component.playwright.PlaywrightComponentFactory.requirePlaywrightContext;

public class PlaywrightInputComponent extends HtmlInputComponent {

    public PlaywrightInputComponent(UiTestComponentContext context) {
        super(context);
    }

    @Override
    public void setValue(String value) {
        var context = requirePlaywrightContext(getContext());

        context.getLocator().fill(value);
        context.evaluateScript("waitUntilInputLoadingFinished.js");
    }

    @Override
    public DataList getDateList() {
        return new PlaywrightDataListComponent(this);
    }
}
