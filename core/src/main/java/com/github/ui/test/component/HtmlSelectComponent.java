package com.github.ui.test.component;

import com.github.ui.test.context.UiTestComponentContext;

import java.util.regex.Pattern;

import static com.github.ui.test.selector.Selectors.byCSSSelector;

public abstract class HtmlSelectComponent extends UiTestComponent {

    protected HtmlSelectComponent(UiTestComponentContext context) {
        super(context);
    }

    public abstract void select(String value);

    public abstract void selectFirstMatching(Pattern pattern);

    public UiTestComponentList<UiTestComponent> getOptions() {
        return getChildList(byCSSSelector("option"));
    }

}
