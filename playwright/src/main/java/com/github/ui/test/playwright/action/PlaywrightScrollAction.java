package com.github.ui.test.playwright.action;

import com.github.ui.test.core.action.UiTestScrollAction;
import com.github.ui.test.playwright.context.PlaywrightComponentContext;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class PlaywrightScrollAction implements UiTestScrollAction {

    private final PlaywrightComponentContext context;

    @Override
    public UiTestScrollAction toTop() {
        context.evaluateScript("/js/action/scrollToTop.js");
        return this;
    }
}
