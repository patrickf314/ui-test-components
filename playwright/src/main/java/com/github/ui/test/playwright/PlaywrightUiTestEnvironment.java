package com.github.ui.test.playwright;

import com.github.ui.test.core.UiTestEnvironment;
import com.github.ui.test.playwright.assertion.PlaywrightAssertFactory;
import com.github.ui.test.playwright.component.PlaywrightComponentFactory;
import com.github.ui.test.playwright.predicate.PlaywrightComponentPredicateFactory;
import com.github.ui.test.playwright.selector.PlaywrightSelectorFactory;
import com.microsoft.playwright.Playwright;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.With;

import java.time.Duration;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class PlaywrightUiTestEnvironment extends UiTestEnvironment {

    private static final String DEFAULT_TEST_ID_ATTRIBUTE = "data-testid";

    private final Options options;
    private final Playwright playwright;
    private final PlaywrightComponentFactory componentFactory = new PlaywrightComponentFactory();
    private final PlaywrightComponentPredicateFactory predicateFactory = new PlaywrightComponentPredicateFactory();
    private final PlaywrightSelectorFactory selectorFactory = new PlaywrightSelectorFactory();
    private final PlaywrightAssertFactory assertFactory = new PlaywrightAssertFactory();

    public static void setup() {
        setup(new Options());
    }

    public static void setup(Options options) {
        ensureCorrectEnvironment(PlaywrightUiTestEnvironment.class, () -> {
            var playwright = Playwright.create();
            return new PlaywrightUiTestEnvironment(options, playwright);
        });
    }

    public static PlaywrightUiTestEnvironment getEnvironment() {
        var instance = UiTestEnvironment.getEnvironment();
        if (instance instanceof PlaywrightUiTestEnvironment playwrightEnvironment) {
            return playwrightEnvironment;
        }

        throw new IllegalStateException("Current UiTestEnvironment is not a playwright environment.");
    }

    public void close() {
        playwright.close();
    }

    @With
    @Getter
    @RequiredArgsConstructor
    public static class Options {
        private final Duration timeout;
        private final String testIdAttribute;

        public Options() {
            this(Duration.ofSeconds(5), DEFAULT_TEST_ID_ATTRIBUTE);
        }
    }
}
