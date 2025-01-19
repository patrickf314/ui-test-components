package com.github.ui.test.playwright.action;

import com.github.ui.test.core.action.UiTestCursorAction;
import com.github.ui.test.playwright.context.PlaywrightComponentContext;
import com.microsoft.playwright.Mouse;
import com.microsoft.playwright.options.BoundingBox;

/**
 * Playwright implementation of the {@link UiTestCursorAction}
 */
public class PlaywrightCursorAction implements UiTestCursorAction {

    private final PlaywrightComponentContext context;
    private final BoundingBox boundingBox;

    /**
     * Constructor.
     *
     * @param context the component context in which the actions are performed.
     */
    public PlaywrightCursorAction(PlaywrightComponentContext context) {
        this.context = context;
        this.boundingBox = context.getLocator().boundingBox();
    }

    @Override
    public UiTestCursorAction move(double dx, double dy) {
        mouse().move(boundingBox.x + dx, boundingBox.y + dy);
        return this;
    }

    @Override
    public UiTestCursorAction down() {
        mouse().down();
        return this;
    }

    @Override
    public UiTestCursorAction up() {
        mouse().up();
        return this;
    }

    private Mouse mouse() {
        return context.getPageContext().getPage().mouse();
    }
}
