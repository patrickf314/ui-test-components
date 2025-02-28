package com.github.ui.test.core.action;

import com.github.ui.test.core.component.UiTestComponent;

/**
 * The action class of the mouse cursor.
 * This class contains the actions the user can perform
 * using the mouse
 */
public interface UiTestCursorAction {

    /**
     * Moves the curser by the specific pixel distance relative to the current
     * position.
     * The cursor action always starts in the top left corner of the component, when
     * {@link UiTestComponent#cursor()} is called.
     *
     * @param dx the pixels in x direction (positive x corresponds to moving to the right)
     * @param dy the pixels in y direction (positive y corresponds to moving downwards)
     * @return this
     */
    UiTestCursorAction move(double dx, double dy);

    /**
     * Performs a mouse press of the left mouse button
     *
     * @return this
     */
    UiTestCursorAction down();

    /**
     * Releases the pressed mouse button
     *
     * @return this
     */
    UiTestCursorAction up();

}
