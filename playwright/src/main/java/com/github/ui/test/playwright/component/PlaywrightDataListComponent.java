package com.github.ui.test.playwright.component;

import com.github.ui.test.core.component.HtmlInputComponent;
import lombok.RequiredArgsConstructor;

import static com.github.ui.test.playwright.component.PlaywrightComponentFactory.requirePlaywrightContext;

@RequiredArgsConstructor
public class PlaywrightDataListComponent implements HtmlInputComponent.DataList {

    private final PlaywrightInputComponent input;

    @Override
    public void selectOptionStartingWith(String prefix) {
        var context = requirePlaywrightContext(input.getContext());
        var option = (String) context.evaluateScript("/js/findOptionStartingWith.js", prefix);

        input.setValue(option);
    }
}
