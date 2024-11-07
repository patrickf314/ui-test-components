package com.github.ui.test.playwright.component;

import com.github.ui.test.core.component.HtmlInputComponent;
import com.github.ui.test.core.context.UiTestComponentContext;

import static com.github.ui.test.playwright.component.PlaywrightComponentFactory.requirePlaywrightContext;

public class PlaywrightInputComponent extends HtmlInputComponent {

    public PlaywrightInputComponent(UiTestComponentContext context) {
        super(context);
    }

    @Override
    public void setValue(String value) {
        var context = requirePlaywrightContext(getContext());

        context.evaluateScript("/js/waitInputEditable.js");
        context.getLocator().fill(value);
        context.evaluateScript("/js/waitUntilLoadingFinished.js");
    }

    @Override
    public DataList getDateList() {
        return new PlaywrightDataListComponent(this);
    }
}
