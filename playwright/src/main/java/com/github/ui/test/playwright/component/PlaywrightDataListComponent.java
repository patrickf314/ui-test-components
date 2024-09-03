package com.github.ui.test.playwright.component;

import com.github.ui.test.core.component.HtmlInputComponent;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class PlaywrightDataListComponent implements HtmlInputComponent.DataList {

    private final PlaywrightInputComponent input;

    @Override
    public void selectOptionStartingWith(String prefix) {
        var option = (String) PlaywrightComponentFactory.requirePlaywrightContext(input.getContext()).evaluateScript("selectOptionStartingWith.js", prefix);
        input.setValue(option);
    }
}
