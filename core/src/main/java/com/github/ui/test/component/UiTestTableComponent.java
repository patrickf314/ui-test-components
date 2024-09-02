package com.github.ui.test.component;

import com.github.ui.test.context.UiTestComponentContext;

import java.util.function.Function;

import static com.github.ui.test.selector.Selectors.byCSSSelector;

public class UiTestTableComponent<T extends UiTestComponent> extends UiTestComponent {

    private final Function<UiTestComponentContext, T> entryConstructor;

    protected UiTestTableComponent(UiTestComponentContext context,
                                   Function<UiTestComponentContext, T> entryConstructor) {
        super(context);

        this.entryConstructor = entryConstructor;
    }

    public UiTestComponentList<UiTestComponent> getHeader() {
        return getChildList(byCSSSelector("thead th"));
    }

    public UiTestComponentList<T> getRows() {
        return getChildList(entryConstructor, byCSSSelector("tbody tr"));
    }

    public T getRow(int row) {
        return getRows().get(row);
    }

    public UiTestComponentList<UiTestComponent> getColumn(int column) {
        return getChildList(UiTestComponent::new, byCSSSelector("tbody tr td:nth-child(" + (column + 1) + ")"));
    }
}
