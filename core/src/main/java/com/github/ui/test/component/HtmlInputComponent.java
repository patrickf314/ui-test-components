package com.github.ui.test.component;

import com.github.ui.test.context.UiTestComponentContext;

public abstract class HtmlInputComponent extends UiTestComponent {

    protected HtmlInputComponent(UiTestComponentContext context) {
        super(context);
    }

    public abstract void setValue(String value);

    public abstract DataList getDateList();

    public interface DataList {

        void selectOptionStartingWith(String value);

    }
}
