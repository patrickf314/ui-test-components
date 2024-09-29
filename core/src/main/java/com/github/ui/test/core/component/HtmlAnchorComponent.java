package com.github.ui.test.core.component;

import com.github.ui.test.core.context.UiTestComponentContext;
import com.github.ui.test.core.context.UiTestPageContext;

import java.util.function.Function;

public class HtmlAnchorComponent extends UiTestComponent {

    public HtmlAnchorComponent(UiTestComponentContext context) {
        super(context);
    }

    public <T extends UiTestPage> T click(Function<UiTestPageContext, T> constructor) {
        click();
        return getContext().waitForPage(constructor);
    }
}
