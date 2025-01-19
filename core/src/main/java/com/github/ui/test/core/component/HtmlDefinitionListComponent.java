package com.github.ui.test.core.component;

import com.github.ui.test.core.action.UiTestCursorAction;
import com.github.ui.test.core.context.UiTestComponentContext;

import static com.github.ui.test.core.predicate.UiTestComponentPredicate.hasText;

/**
 * Abstract base implement of the {@link UiTestComponent} for HTML definition lists {@code <dl>}.
 *
 * @param <T> the component type of the title elements ({@code <dt>})
 * @param <D> the component type of the description elements ({@code <dd>})
 * @see UiTestComponent#definitionList()
 */
public abstract class HtmlDefinitionListComponent<T extends UiTestComponent, D extends UiTestComponent> extends UiTestComponent {

    /**
     * Constructor
     *
     * @param context the component context
     */
    protected HtmlDefinitionListComponent(UiTestComponentContext context) {
        super(context);
    }

    /**
     * Getter for the {@link UiTestComponentList} of all title elements ({@code <dt>})
     *
     * @return the list of title elements
     */
    public abstract UiTestComponentList<T> getTitles();

    /**
     * Getter for the {@link UiTestComponentList} of all description elements ({@code <dd>})
     *
     * @return the list of description elements
     */
    public abstract UiTestComponentList<D> getDescriptions();

    /**
     * Getter for the {@link UiTestComponentList} of all entries (Combination of the title {@code <dt>} and the directly succeeding description {@code <dd>})
     *
     * @return the list of entries
     */
    public abstract UiTestComponentList<Entry<T, D>> getEntries();

    /**
     * Getter for the entry by the text of the title element.
     * <p>
     * NOTE: this function fails if the title is not unique.
     *
     * @param title the title
     * @return the entry
     */
    public Entry<T, D> getByTitle(String title) {
        return getEntries().filter(hasText(title)).singleton();
    }

    /**
     * Getter for the description by the text of the title element.
     * <p>
     * NOTE: this function fails if the title is not unique.
     *
     * @param title the title
     * @return the description
     */
    public D getDescriptionOf(String title) {
        return getByTitle(title).getDescription();
    }

    /**
     * Abstract base class of an entry in a {@link HtmlDefinitionListComponent}.
     * An entry is the combination of the title ({@code <dt>}) and the directly succeeding description ({@code <dd>})
     *
     * @param <T> the component type of the title element ({@code <dt>})
     * @param <D> the component type of the description element ({@code <dd>})
     */
    public abstract static class Entry<T extends UiTestComponent, D extends UiTestComponent> extends UiTestComponent {

        /**
         * Constructor
         *
         * @param context the component context of the title element
         */
        protected Entry(UiTestComponentContext context) {
            super(context);
        }

        @Override
        public void click() {
            throw new UnsupportedOperationException("Clicking a HtmlDefinitionListComponent.Entry is not supported.");
        }

        @Override
        public UiTestCursorAction cursor() {
            throw new UnsupportedOperationException("Cursor action on a HtmlDefinitionListComponent.Entry is not supported.");
        }

        /**
         * Getter for the title element
         *
         * @return the title element
         */
        public abstract T getTitle();

        /**
         * Getter for the description element
         *
         * @return the description element
         */
        public abstract D getDescription();

    }

}
