package com.github.ui.test.component.playwright;

import com.github.ui.test.component.HtmlSelectComponent;
import com.github.ui.test.context.UiTestComponentContext;

import java.util.regex.Pattern;

import static com.github.ui.test.component.playwright.PlaywrightComponentFactory.requirePlaywrightContext;
import static com.github.ui.test.predicate.UiTestComponentPredicates.matches;

public class PlaywrightSelectComponent extends HtmlSelectComponent {

    public PlaywrightSelectComponent(UiTestComponentContext context) {
        super(context);
    }

    @Override
    public void select(String value) {
        requirePlaywrightContext(getContext()).getLocator().selectOption(value);
    }

    @Override
    public void selectFirstMatching(Pattern pattern) {
        var value = requirePlaywrightContext(getOptions().first(matches(pattern)).getContext()).getLocator().textContent();
        select(value);
    }
}
