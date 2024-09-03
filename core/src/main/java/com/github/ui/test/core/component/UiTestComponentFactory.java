package com.github.ui.test.core.component;

import com.github.ui.test.core.context.UiTestComponentContext;

import java.util.function.Function;

public interface UiTestComponentFactory {

    HtmlInputComponent inputComponent(UiTestComponentContext context);

    HtmlCheckboxComponent checkboxComponent(UiTestComponentContext context);

    HtmlTextAreaComponent textAreaComponent(UiTestComponentContext context);

    UiTestComponent buttonComponent(UiTestComponentContext context);

    HtmlSelectComponent selectComponent(UiTestComponentContext context);

    <T extends UiTestComponent, D extends UiTestComponent> DefinitionListComponent<T, D> definitionListComponent(UiTestComponentContext context, Function<UiTestComponentContext, T> titleConstructor, Function<UiTestComponentContext, D> descriptionConstructor);

    default <T extends UiTestComponent> UiTestTableComponent<T> tableComponent(UiTestComponentContext context, Function<UiTestComponentContext, T> entryConstructor) {
        return new UiTestTableComponent<>(context, entryConstructor);
    }
}
