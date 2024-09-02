package com.github.ui.test.component.playwright;

import com.github.ui.test.component.HtmlInputComponent;
import lombok.RequiredArgsConstructor;

import static com.github.ui.test.component.playwright.PlaywrightComponentFactory.requirePlaywrightContext;

@RequiredArgsConstructor
public class PlaywrightDataListComponent implements HtmlInputComponent.DataList {

    private final PlaywrightInputComponent input;

    @Override
    public void selectOptionStartingWith(String prefix) {
        var option = (String) requirePlaywrightContext(input.getContext()).evaluateScript("selectOptionStartingWith.js", prefix);
        input.setValue(option);
    }
}
