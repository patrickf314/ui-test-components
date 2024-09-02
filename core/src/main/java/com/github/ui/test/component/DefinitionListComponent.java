package com.github.ui.test.component;

import com.github.ui.test.context.UiTestComponentContext;

import static com.github.ui.test.predicate.UiTestComponentPredicates.hasText;

public abstract class DefinitionListComponent<T extends UiTestComponent, D extends UiTestComponent> extends UiTestComponent {

    protected DefinitionListComponent(UiTestComponentContext context) {
        super(context);
    }

    public abstract UiTestComponentList<T> getTitles();

    public abstract UiTestComponentList<D> getDescriptions();

    public abstract UiTestComponentList<Entry<T, D>> getEntries();

    public Entry<T, D> getByTitle(String title) {
        return getEntries().filter(hasText(title)).singleton();
    }

    public D getDescriptionOf(String title) {
        return getByTitle(title).getDescription();
    }

    public abstract static class Entry<T extends UiTestComponent, D extends UiTestComponent> extends UiTestComponent {

        protected Entry(UiTestComponentContext context) {
            super(context);
        }

        public abstract T getTitle();

        public abstract D getDescription();

    }

}
