package com.github.ui.test.playwright.predicate;

import org.jeasy.random.EasyRandom;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static com.github.ui.test.playwright.PlaywrightTestUtils.mockPage;
import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

/**
 * Unit test for {@link PlaywrightComponentHasTextPredicate}
 */
class PlaywrightComponentHasTextPredicateTest {

    private static final EasyRandom RANDOM = new EasyRandom();

    @ParameterizedTest
    @ValueSource(booleans = {true, false})
    void testMatch(boolean not) {
        var text = RANDOM.nextObject(String.class);
        var predicate = new PlaywrightComponentHasTextPredicate(not, text);

        // language=HTML
        try (var context = mockPage("""
                <html lang="en">
                <body>
                <h1>%s</h1>
                </body>
                </html>
                """.formatted(text))) {

            var filteredLocator = predicate.filter(context.page(), context.page().locator("h1"));
            assertThat(filteredLocator).hasCount(not ? 0 : 1);
        }
    }

    @ParameterizedTest
    @ValueSource(booleans = {true, false})
    void testFilter(boolean not) {
        var text = RANDOM.nextObject(String.class);
        var predicate = new PlaywrightComponentHasTextPredicate(not, text);

        // language=HTML
        try (var context = mockPage("""
                <html lang="en">
                <body>
                <ul>
                <li>%s</li>
                <li>%s</li>
                <li>%s</li>
                <li>%s</li>
                <li>%s</li>
                </ul>
                </body>
                </html>
                """.formatted(text, text, text, "different than " + text, "also different than " + text))) {

            var filteredLocator = predicate.filter(context.page(), context.page().locator("li"));
            assertThat(filteredLocator).hasCount(not ? 2 : 3);
        }
    }
}