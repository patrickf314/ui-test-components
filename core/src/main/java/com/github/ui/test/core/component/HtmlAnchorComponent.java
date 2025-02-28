package com.github.ui.test.core.component;

import com.github.ui.test.core.context.UiTestComponentContext;
import com.github.ui.test.core.context.UiTestPageContext;

import java.util.function.Function;

/**
 * {@link UiTestComponent} implementation for an HTML anchor {@code <a>}
 *
 * @see UiTestComponent#anchorComponent()
 */
public class HtmlAnchorComponent extends UiTestComponent {

    /**
     * Constructor.
     * Use {@link UiTestComponent#anchorComponent()} to create this component.
     *
     * @param context the component context
     */
    protected HtmlAnchorComponent(UiTestComponentContext context) {
        super(context);
    }

    /**
     * Clicks the anchor and waits for the new page
     *
     * @param constructor the page constructor
     * @param <T>         the {@link UiTestPage} type
     * @return the new page
     */
    public <T extends UiTestPage> T click(Function<UiTestPageContext, T> constructor) {
        click();
        return getContext().waitForPage(constructor);
    }
}
