package com.github.ui.test.playwright.predicate;

import org.jeasy.random.EasyRandom;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static com.github.ui.test.playwright.PlaywrightTestUtils.mockPage;
import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

/**
 * Unit test for {@link PlaywrightComponentHasTextPredicate}
 */
class PlaywrightComponentHasAttributePredicateTest {

    private static final EasyRandom RANDOM = new EasyRandom();
    private static final String TEST_ATTRIBUTE = "data-test";

    @ParameterizedTest
    @ValueSource(booleans = {true, false})
    void testMatchWithValue(boolean not) {
        var value = RANDOM.nextObject(String.class);
        var predicate = new PlaywrightComponentHasAttributePredicate(not, TEST_ATTRIBUTE, value);

        // language=HTML
        try (var context = mockPage("""
                <html lang="en">
                <body>
                <h1 data-test='%s'>Test</h1>
                </body>
                </html>
                """.formatted(value))) {

            var filteredLocator = predicate.filter(context.page(), context.page().locator("h1"));
            assertThat(filteredLocator).hasCount(not ? 0 : 1);
        }
    }

    @ParameterizedTest
    @ValueSource(booleans = {true, false})
    void testFilterWithValue(boolean not) {
        var value = RANDOM.nextObject(String.class);
        var predicate = new PlaywrightComponentHasAttributePredicate(not, TEST_ATTRIBUTE, value);

        // language=HTML
        try (var context = mockPage("""
                <html lang="en">
                <body>
                <ul>
                <li data-test='%s'>Item 1</li>
                <li data-test>Item 2</li>
                <li>Item 3</li>
                <li data-test='%s'>Item 4</li>
                <li data-test='%s'>Item 5</li>
                </ul>
                </body>
                </html>
                """.formatted(value, value, "different than " + value))) {

            var filteredLocator = predicate.filter(context.page(), context.page().locator("li"));
            assertThat(filteredLocator).hasCount(not ? 3 : 2);
        }
    }

    @Test
    void testDoesNotMatchChildElements() {
        var value = RANDOM.nextObject(String.class);
        var predicate = new PlaywrightComponentHasAttributePredicate(false, TEST_ATTRIBUTE, value);

        // language=HTML
        try (var context = mockPage("""
                <html lang="en">
                <body>
                <h1><span data-test='%s'>Test</span></h1>
                </body>
                </html>
                """.formatted(value))) {

            var filteredLocator = predicate.filter(context.page(), context.page().locator("h1"));
            assertThat(filteredLocator).hasCount(0);
        }
    }
}