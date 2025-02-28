package com.github.ui.test.playwright.action;

import com.github.ui.test.core.action.UiTestCursorAction;
import com.github.ui.test.playwright.context.PlaywrightComponentContext;
import com.microsoft.playwright.Mouse;

/**
 * Playwright implementation of the {@link UiTestCursorAction}
 */
public class PlaywrightCursorAction implements UiTestCursorAction {

    private final PlaywrightComponentContext context;

    private boolean hasMoved;
    private double x;
    private double y;

    /**
     * Constructor.
     *
     * @param context the component context in which the actions are performed.
     */
    public PlaywrightCursorAction(PlaywrightComponentContext context) {
        this.context = context;

        var boundingBox = context.getLocator().boundingBox();
        this.x = boundingBox.x;
        this.y = boundingBox.y;
    }

    @Override
    public UiTestCursorAction move(double dx, double dy) {
        x += dx;
        y += dy;
        mouse().move(x, y);
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
        var mouse = context.getPageContext().getPage().mouse();
        if (!hasMoved) {
            mouse.move(x, y);
            hasMoved = true;
        }
        return mouse;
    }
}
