package com.github.ui.test.playwright.component;

import com.github.ui.test.core.component.HtmlDefinitionListComponent;
import com.github.ui.test.core.component.UiTestComponent;
import com.github.ui.test.core.component.UiTestComponentList;
import com.github.ui.test.core.context.UiTestComponentContext;

import java.util.function.Function;

import static com.github.ui.test.core.selector.Selector.byCSSSelector;

public class PlaywrightDefinitionListComponent<T extends UiTestComponent, D extends UiTestComponent> extends HtmlDefinitionListComponent<T, D> {

    private final Function<UiTestComponentContext, T> titleConstructor;
    private final Function<UiTestComponentContext, D> descriptionConstructor;

    protected PlaywrightDefinitionListComponent(UiTestComponentContext context,
                                                Function<UiTestComponentContext, T> titleConstructor,
                                                Function<UiTestComponentContext, D> descriptionConstructor) {
        super(context);

        this.titleConstructor = titleConstructor;
        this.descriptionConstructor = descriptionConstructor;
    }

    @Override
    public UiTestComponentList<T> getTitles() {
        return getChildList(titleConstructor, byCSSSelector("dt"));
    }

    @Override
    public UiTestComponentList<D> getDescriptions() {
        return getChildList(descriptionConstructor, byCSSSelector("dd"));
    }

    @Override
    public UiTestComponentList<Entry<T, D>> getEntries() {
        return getChildList(this::entry, byCSSSelector("dt"));
    }

    private PlaywrightDefinitionListEntryComponent<T, D> entry(UiTestComponentContext context) {
        return new PlaywrightDefinitionListEntryComponent<>(PlaywrightComponentFactory.requirePlaywrightContext(context), titleConstructor, descriptionConstructor);
    }
}
