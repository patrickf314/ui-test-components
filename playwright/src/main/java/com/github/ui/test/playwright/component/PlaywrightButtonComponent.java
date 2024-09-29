
package com.github.ui.test.playwright.component;

import com.github.ui.test.core.component.UiTestComponent;
import com.github.ui.test.core.context.UiTestComponentContext;

import static com.github.ui.test.playwright.component.PlaywrightComponentFactory.requirePlaywrightContext;

public class PlaywrightButtonComponent extends UiTestComponent {

    public PlaywrightButtonComponent(UiTestComponentContext context) {
        super(context);
    }

    @Override
    public void click() {
        var context = requirePlaywrightContext(getContext());
        context.evaluateScript("/js/waitUntilButtonIsEnabled.js");

        super.click();
    }
}
