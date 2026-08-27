package com.github.ui.test.playwright.component;

import com.github.ui.test.core.component.CopyToClipboardComponent;
import com.github.ui.test.core.context.UiTestComponentContext;

import static com.github.ui.test.playwright.component.PlaywrightComponentFactory.requirePlaywrightContext;

public class PlaywrightCopyToClipboardComponent extends CopyToClipboardComponent {

    /**
     * Constructor
     *
     * @param context the component context
     */
    public PlaywrightCopyToClipboardComponent(UiTestComponentContext context) {
        super(context);
    }

    @Override
    public String clickAndGetCopiedText() {
        var context = requirePlaywrightContext(getContext());

        super.click();

        return (String) context.getLocator().evaluate("navigator.clipboard.readText()");
    }
}
