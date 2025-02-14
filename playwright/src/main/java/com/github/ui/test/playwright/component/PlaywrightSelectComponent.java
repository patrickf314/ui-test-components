package com.github.ui.test.playwright.component;

import com.github.ui.test.core.component.HtmlSelectComponent;
import com.github.ui.test.core.context.UiTestComponentContext;

import java.util.Map;
import java.util.regex.Pattern;

import static com.github.ui.test.core.predicate.UiTestComponentPredicate.matches;
import static com.github.ui.test.playwright.component.PlaywrightComponentFactory.requirePlaywrightContext;

public class PlaywrightSelectComponent extends HtmlSelectComponent {

    public PlaywrightSelectComponent(UiTestComponentContext context) {
        super(context);
    }

    @Override
    public void select(String value) {
        requirePlaywrightContext(getContext()).getLocator().selectOption(value);
    }

    @Override
    public void select(String group, String label) {
        var context = requirePlaywrightContext(getContext());
        var value = (String) context.evaluateScript("/js/findOptionValueInGroup.js", Map.of("group", group, "label", label));

        context.evaluateScript("/js/waitUntilElementIsNotOverlapped.js");
        context.getLocator().selectOption(value);
    }

    @Override
    public void selectFirstMatching(Pattern pattern) {
        var value = requirePlaywrightContext(getOptions().first(matches(pattern)).getContext()).getLocator().textContent();
        select(value);
    }
}
