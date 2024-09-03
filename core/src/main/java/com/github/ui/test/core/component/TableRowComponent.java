package com.github.ui.test.core.component;

import com.github.ui.test.core.context.UiTestComponentContext;
import com.github.ui.test.core.selector.Selector;

import java.util.function.Function;

import static com.github.ui.test.core.UiTestEnvironment.byCSSSelector;

public class TableRowComponent extends UiTestComponent {

    public TableRowComponent(UiTestComponentContext context) {
        super(context);
    }

    protected static Selector cellSelector(int column) {
        return byCSSSelector("td").nth(column);
    }

    public UiTestComponent getCell(int column) {
        return getCell(UiTestComponent::new, column);
    }

    protected <T extends UiTestComponent> T getCell(Function<UiTestComponentContext, T> component, int column) {
        return getChild(component, cellSelector(column));
    }

    protected <T extends UiTestComponent> T getInCell(Function<UiTestComponentContext, T> component, int column, Selector selector) {
        return getChild(component, cellSelector(column), selector);
    }
}
