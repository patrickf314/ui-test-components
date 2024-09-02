package com.github.ui.test.component.playwright;

import com.github.ui.test.component.DefinitionListComponent;
import com.github.ui.test.component.UiTestComponent;
import com.github.ui.test.context.UiTestComponentContext;

import java.util.function.Function;

import static com.github.ui.test.selector.Selectors.byXPath;

public class PlaywrightDefinitionListEntryComponent<T extends UiTestComponent, D extends UiTestComponent> extends DefinitionListComponent.Entry<T, D> {

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
