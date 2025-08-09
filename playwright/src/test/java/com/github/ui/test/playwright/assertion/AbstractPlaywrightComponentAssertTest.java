package com.github.ui.test.playwright.assertion;

import com.github.ui.test.playwright.predicate.PlaywrightComponentHasTextPredicate;
import org.junit.jupiter.api.Test;
import org.opentest4j.AssertionFailedError;

import static com.github.ui.test.playwright.PlaywrightTestUtils.mockPage;
import static org.assertj.core.api.Assertions.*;

/**
 * Unit test for {@link AbstractPlaywrightComponentAssert}
 */
class AbstractPlaywrightComponentAssertTest {

    @Test
    void testSatisfies() {
        // language=HTML
        try (var context = mockPage("""
                <h1>Text</h1>
                """)) {

            var componentAssert = new PlaywrightComponentAssert(context.component("h1"));

            assertThatCode(() -> componentAssert.satisfies(new PlaywrightComponentHasTextPredicate(false, "Text")))
                    .doesNotThrowAnyException();
            assertThatThrownBy(() -> componentAssert.satisfies(new PlaywrightComponentHasTextPredicate(false, "Some other text")))
                    .hasMessage("""
                            Locator expected to have count: 1
                            Received: 0
                            
                            Call log:
                              - Locator.expect with timeout 1000ms
                              - waiting for locator("h1").filter(new Locator.FilterOptions().setHas(getByText("Some other text", new Page.GetByTextOptions().setExact(true))))
                                5 × locator resolved to 0 elements
                                  - unexpected value "0\"""")
                    .isInstanceOfSatisfying(AssertionFailedError.class, error -> {
                        assertThat(error.getActual().getStringRepresentation()).isEqualTo("text('Text')");
                        assertThat(error.getExpected().getStringRepresentation()).isEqualTo("text('Some other text')");
                    });
        }
    }

    @Test
    void testDoesNotSatisfies() {
        // language=HTML
        try (var context = mockPage("""
                <h1>Text</h1>
                """)) {

            var componentAssert = new PlaywrightComponentAssert(context.component("h1"));

            assertThatCode(() -> componentAssert.doesNotSatisfy(new PlaywrightComponentHasTextPredicate(false, "Some other text")))
                    .doesNotThrowAnyException();
            assertThatThrownBy(() -> componentAssert.doesNotSatisfy(new PlaywrightComponentHasTextPredicate(false, "Text")))
                    .hasMessage("""
                            Locator expected to have count: 0
                            Received: 1
                            
                            Call log:
                              - Locator.expect with timeout 1000ms
                              - waiting for locator("h1").filter(new Locator.FilterOptions().setHas(getByText("Text", new Page.GetByTextOptions().setExact(true))))
                                5 × locator resolved to 1 element
                                  - unexpected value "1\"""")
                    .isInstanceOfSatisfying(AssertionFailedError.class, error -> {
                        assertThat(error.getActual().getStringRepresentation()).isEqualTo("text('Text')");
                        assertThat(error.getExpected().getStringRepresentation()).isEqualTo("not text('Text')");
                    });
        }
    }
}