package com.github.ui.test.core.component;

import com.github.ui.test.core.context.UiTestComponentContext;

import java.util.function.Function;

import static com.github.ui.test.core.predicate.UiTestComponentPredicate.hasText;
import static com.github.ui.test.core.selector.Selector.byCSSSelector;


public class HtmlTableComponent<T extends UiTestComponent> extends UiTestComponent {

    private final Function<UiTestComponentContext, T> entryConstructor;

    protected HtmlTableComponent(UiTestComponentContext context,
                                 Function<UiTestComponentContext, T> entryConstructor) {
        super(context);

        this.entryConstructor = entryConstructor;
    }

    public UiTestComponentList<UiTestComponent> getHeader() {
        return getChildList(byCSSSelector("thead th"));
    }

    public void clickHeader(String text) {
        this.getHeader().singleton(hasText(text)).click();
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
