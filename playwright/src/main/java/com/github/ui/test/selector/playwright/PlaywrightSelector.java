package com.github.ui.test.selector.playwright;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.github.ui.test.selector.Selector;

public interface PlaywrightSelector extends Selector {

    @Override
    PlaywrightSelector nth(int n);

    Locator asChildLocatorOf(Locator locator);

    Locator locateOn(Page page);
}