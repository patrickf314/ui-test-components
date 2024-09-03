
package com.github.ui.test.playwright.component;

import com.github.ui.test.core.component.UiTestComponent;
import com.github.ui.test.core.context.UiTestComponentContext;

public class PlaywrightButtonComponent extends UiTestComponent {

    public PlaywrightButtonComponent(UiTestComponentContext context) {
        super(context);
    }

    @Override
    public void click() {
        PlaywrightComponentFactory.requirePlaywrightContext(getContext()).evaluateScript("waitUntilButtonIsEnabled.js");
        super.click();
    }
}
