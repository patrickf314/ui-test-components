package com.github.ui.test.playwright.component;

import com.github.ui.test.core.component.HtmlDefinitionListComponent;
import com.github.ui.test.core.component.UiTestComponent;
import com.github.ui.test.core.context.UiTestComponentContext;

import java.util.function.Function;

import static com.github.ui.test.core.selector.Selector.byXPath;

public class PlaywrightDefinitionListEntryComponent<T extends UiTestComponent, D extends UiTestComponent> extends HtmlDefinitionListComponent.Entry<T, D> {

    private final Function<UiTestComponentContext, T> titleConstructor;
    private final Function<UiTestComponentContext, D> descriptionConstructor;

    protected PlaywrightDefinitionListEntryComponent(UiTestComponentContext context,
                                                     Function<UiTestComponentContext, T> titleConstructor,
                                                     Function<UiTestComponentContext, D> descriptionConstructor) {
        super(context);

        this.titleConstructor = titleConstructor;
        this.descriptionConstructor = descriptionConstructor;
    }

    @Override
    public T getTitle() {
        return titleConstructor.apply(getContext());
    }

    @Override
    public D getDescription() {
        return getChild(descriptionConstructor, byXPath("following-sibling::dd[1]"));
    }
}
