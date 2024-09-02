package com.github.ui.test.component.playwright;

import com.github.ui.test.component.DefinitionListComponent;
import com.github.ui.test.component.UiTestComponent;
import com.github.ui.test.component.UiTestComponentList;
import com.github.ui.test.context.UiTestComponentContext;

import java.util.function.Function;

import static com.github.ui.test.component.playwright.PlaywrightComponentFactory.requirePlaywrightContext;
import static com.github.ui.test.selector.Selectors.byCSSSelector;

public class PlaywrightDefinitionListComponent<T extends UiTestComponent, D extends UiTestComponent> extends DefinitionListComponent<T, D> {

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
        return new PlaywrightDefinitionListEntryComponent<>(requirePlaywrightContext(context), titleConstructor, descriptionConstructor);
    }
}
