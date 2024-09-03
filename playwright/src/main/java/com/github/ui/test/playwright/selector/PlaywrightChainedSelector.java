package com.github.ui.test.playwright.selector;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.github.ui.test.core.selector.Selector;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class PlaywrightChainedSelector implements PlaywrightSelector {

    private final List<PlaywrightSelector> selectors;

    @Override
    public String toString() {
        return selectors.stream().map(Selector::toString).collect(Collectors.joining(" "));
    }

    @Override
    public PlaywrightSelector nth(int n) {
        var lastIndex = selectors.size() - 1;
        var othersCopy = new ArrayList<>(selectors);
        var last = othersCopy.remove(lastIndex);

        othersCopy.add(last.nth(n));

        return new PlaywrightChainedSelector(selectors);
    }

    @Override
    public Locator asChildLocatorOf(Locator locator) {
        var childLocator = locator;
        for(var selector : selectors) {
            childLocator = selector.asChildLocatorOf(childLocator);
        }
        return childLocator;
    }

    @Override
    public Locator locateOn(Page page) {
        var locator = selectors.get(0).locateOn(page);
        for(var i = 1; i < selectors.size(); i++) {
            locator = selectors.get(i).asChildLocatorOf(locator);
        }
        return locator;
    }
}
