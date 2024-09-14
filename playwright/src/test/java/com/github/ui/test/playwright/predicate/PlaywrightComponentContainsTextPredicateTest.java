package com.github.ui.test.playwright.predicate;

import org.jeasy.random.EasyRandom;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Locale;

import static com.github.ui.test.playwright.PlaywrightTestUtils.mockPage;
import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

/**
 * Unit test for {@link PlaywrightComponentContainsTextPredicate}
 */
class PlaywrightComponentContainsTextPredicateTest {

    private static final EasyRandom RANDOM = new EasyRandom();

    @ParameterizedTest
    @ValueSource(booleans = {true, false})
    void testMatch(boolean not) {
        var text = RANDOM.nextObject(String.class);
        var predicate = new PlaywrightComponentContainsTextPredicate(not, text);

        // language=HTML
        try (var context = mockPage("""
                <html lang="en">
                <body>
                <h1>a text containing %s</h1>
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
        var predicate = new PlaywrightComponentContainsTextPredicate(not, text);

        // language=HTML
        try (var context = mockPage("""
                <html lang="en">
                <body>
                <ul>
                <li>first item contains %s</li>
                <li>also contains %s</li>
                <li>does not contain the specific string</li>
                <li>contains %s in upper case</li>
                <li>contains %s in lower case</li>
                </ul>
                </body>
                </html>
                """.formatted(text, text, text.toLowerCase(Locale.ROOT), text.toLowerCase(Locale.ROOT)))) {

            var filteredLocator = predicate.filter(context.page(), context.page().locator("li"));
            assertThat(filteredLocator).hasCount(not ? 1 : 4);
        }
    }
}