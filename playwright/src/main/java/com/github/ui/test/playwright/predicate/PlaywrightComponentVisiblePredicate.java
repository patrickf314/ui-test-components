package com.github.ui.test.playwright.predicate;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class PlaywrightComponentVisiblePredicate implements PlaywrightComponentPredicate {

    public static final String VISIBLE = "<visible>";
    public static final String INVISIBLE = "<invisible>";

    private final boolean not;

    @Override
    public String describeExpected() {
        return not ? INVISIBLE : VISIBLE;
    }

    @Override
    public String describeActual(Locator locator) {
        return locator.isVisible() ? VISIBLE : INVISIBLE;
    }

    @Override
    public PlaywrightComponentPredicate negate() {
        return new PlaywrightComponentVisiblePredicate(!not);
    }

    @Override
    public Locator filter(Page page, Locator locator) {
        return locator.locator("visible=" + !not);
    }
}
