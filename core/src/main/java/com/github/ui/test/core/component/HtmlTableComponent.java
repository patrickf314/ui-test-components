package com.github.ui.test.core.component;

import com.github.ui.test.core.context.UiTestComponentContext;

import java.util.function.Function;

import static com.github.ui.test.core.predicate.UiTestComponentPredicate.hasText;
import static com.github.ui.test.core.selector.Selector.byCSSSelector;

/**
 * {@link UiTestComponent} implementation for an HTML table {@code <table>}.
 * <p>
 * This component should be directly applicable to the most use cases.
 * Create a custom {@link UiTestComponent} implementation for the {@code <tr>}
 * elements contained in this table and then use {@code UiTestComponent.tableComponent(CustomComponent::new)}
 * to construct a HtmlTableComponent for the specific row type.
 *
 * @param <T> the type of the row component
 * @see UiTestComponent#tableComponent(Function)
 */
public class HtmlTableComponent<T extends UiTestComponent> extends UiTestComponent {

    private final Function<UiTestComponentContext, T> entryConstructor;

    /**
     * Constructor.
     * Use {@link UiTestComponent#tableComponent(Function)} to create this component.
     *
     * @param context          the component context
     * @param entryConstructor the constructor for the table rows
     */
    protected HtmlTableComponent(UiTestComponentContext context, Function<UiTestComponentContext, T> entryConstructor) {
        super(context);

        this.entryConstructor = entryConstructor;
    }

    /**
     * Getter for the table header {@code <th>}
     *
     * @return a {@link UiTestComponentList} of the table header cells
     */
    public UiTestComponentList<UiTestComponent> getHeader() {
        return getChildList(byCSSSelector("> thead > th"));
    }

    /**
     * Performs a click on the table header with the specific text.
     *
     * @param text the text of the header cell
     * @see com.github.ui.test.core.predicate.UiTestComponentPredicate#hasText(String)
     */
    public void clickHeader(String text) {
        this.getHeader().singleton(hasText(text)).click();
    }

    /**
     * Getter for all rows of this table.
     *
     * @return a {@link UiTestComponentList} of the table rows
     */
    public UiTestComponentList<T> getRows() {
        return getChildList(entryConstructor, byCSSSelector("> tbody > tr"));
    }

    /**
     * Getter for a specific row in this table.
     *
     * @param row the table row index
     * @return the table row
     */
    public T getRow(int row) {
        return getRows().get(row);
    }

    /**
     * Getter for a specific column in this table.
     * This function does not consider the table header cell.
     *
     * @param column the table column index
     * @return a {@link UiTestComponentList} of the table columns
     */
    public UiTestComponentList<UiTestComponent> getColumn(int column) {
        return getChildList(UiTestComponent::new, byCSSSelector("> tbody > tr > td:nth-child(" + (column + 1) + ")"));
    }
}
