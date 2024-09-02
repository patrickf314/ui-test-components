package com.github.ui.test.selector.playwright;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.function.Function;

@Getter
@RequiredArgsConstructor
public class PlaywrightLocatorSelector implements PlaywrightSelector {

    private final String description;
    private final Function<Page, Locator> pageSelector;
    private final Function<Locator, Locator> locatorSelector;

    @Override
    public String toString() {
        return description;
    }

    @Override
    public PlaywrightSelector nth(int n) {
        return new PlaywrightChildSelector(this, n);
    }

    @Override
    public Locator asChildLocatorOf(Locator locator) {
        return locatorSelector.apply(locator);
    }

    @Override
    public Locator locateOn(Page page) {
        return pageSelector.apply(page);
    }
}
