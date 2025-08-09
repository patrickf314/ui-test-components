package com.github.ui.test.playwright.action;

import org.junit.jupiter.api.Test;

import static com.github.ui.test.playwright.PlaywrightTestUtils.mockPage;
import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

/**
 * Unit test for {@link PlaywrightCursorAction}
 */
class PlaywrightCursorActionTest {

    @Test
    void testActionStartesAtTopLeft() {
        try(var context = mockPage(getClass().getResourceAsStream("/html/cursor-action-test.html"))) {
            context.component("#container").cursor().down().up();

            assertThat(context.locator("#startOutput")).hasText("(0, 0)");
            assertThat(context.locator("#endOutput")).hasText("(0, 0)");
        }
    }

    @Test
    void testMoveAction() {
        try(var context = mockPage(getClass().getResourceAsStream("/html/cursor-action-test.html"))) {
            context.component("#container").cursor().move(10, 10).down().move(200, 100).up();

            assertThat(context.locator("#startOutput")).hasText("(10, 10)");
            assertThat(context.locator("#endOutput")).hasText("(210, 110)");
        }
    }
}