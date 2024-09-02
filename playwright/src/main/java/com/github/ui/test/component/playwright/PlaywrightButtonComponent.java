
package com.github.ui.test.component.playwright;

import com.github.ui.test.component.UiTestComponent;
import com.github.ui.test.context.UiTestComponentContext;

import static com.github.ui.test.component.playwright.PlaywrightComponentFactory.requirePlaywrightContext;

public class PlaywrightButtonComponent extends UiTestComponent {

    public PlaywrightButtonComponent(UiTestComponentContext context) {
        super(context);
    }

    @Override
    public void click() {
        requirePlaywrightContext(getContext()).evaluateScript("waitUntilButtonIsEnabled.js");
        super.click();
    }
}
