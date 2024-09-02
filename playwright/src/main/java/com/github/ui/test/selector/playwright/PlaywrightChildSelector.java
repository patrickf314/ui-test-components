package com.github.ui.test.selector.playwright;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class PlaywrightChildSelector implements PlaywrightSelector {

    private final PlaywrightSelector parentSelector;
    private final int index;

    @Override
    public PlaywrightSelector nth(int n) {
        if (n == 0) {
            return this;
        } else {
            throw new IndexOutOfBoundsException("Cannot get " + n + "-th child from unique selector");
        }
    }

    @Override
    public Locator asChildLocatorOf(Locator locator) {
        return parentSelector.asChildLocatorOf(locator).nth(index);
    }

    @Override
    public Locator locateOn(Page page) {
        return parentSelector.locateOn(page).nth(index);
    }
}
