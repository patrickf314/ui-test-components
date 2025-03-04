package com.github.ui.test.playwright.page;

import com.github.ui.test.core.component.HtmlInputComponent;
import com.github.ui.test.core.component.UiTestComponent;
import com.github.ui.test.core.component.UiTestPage;
import com.github.ui.test.core.context.UiTestPageContext;

import java.util.Map;

import static com.github.ui.test.core.component.UiTestComponent.inputComponent;
import static com.github.ui.test.core.selector.Selector.byCSSSelector;

public class TestPage extends UiTestPage {

    public TestPage(UiTestPageContext context) {
        super(context);
    }

    @Override
    public boolean matchesUrl(UiTestPageContext.Url url) {
        return "/test.html".equals(url.path());
    }

    public HtmlInputComponent getInputField() {
        return getChild(inputComponent(), byCSSSelector("#input"));
    }

    public UiTestComponent getSubmitButton() {
        return getChild(UiTestComponent::new, byCSSSelector("#submit"));
    }

    public UiTestComponent getOutput() {
        return getChild(UiTestComponent::new, byCSSSelector("#output"));
    }
}
