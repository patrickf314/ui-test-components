package com.github.ui.test.component;

import com.github.ui.test.context.UiTestComponentContext;
import com.github.ui.test.context.UiTestPageContext;

import java.util.function.Function;

public class UiTestAnchorComponent extends UiTestComponent {

    public UiTestAnchorComponent(UiTestComponentContext context) {
        super(context);
    }

    public <T extends UiTestPage> T click(Function<UiTestPageContext, T> constructor) {
        click();
        return getContext().waitForPage(constructor);
    }
}
