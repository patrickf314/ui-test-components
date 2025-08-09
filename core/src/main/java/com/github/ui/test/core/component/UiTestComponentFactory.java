package com.github.ui.test.core.component;

import com.github.ui.test.core.context.UiTestComponentContext;

import java.util.function.Function;

public interface UiTestComponentFactory {

    HtmlInputComponent inputComponent(UiTestComponentContext context);

    HtmlCheckboxComponent checkboxComponent(UiTestComponentContext context);

    HtmlTextAreaComponent textAreaComponent(UiTestComponentContext context);

    default HtmlButtonComponent buttonComponent(UiTestComponentContext context) {
        return new HtmlButtonComponent(context);
    }

    default HtmlAnchorComponent anchorComponent(UiTestComponentContext context) {
        return new HtmlAnchorComponent(context);
    }

    HtmlSelectComponent selectComponent(UiTestComponentContext context);

    HtmlFileInputComponent fileInputComponent(UiTestComponentContext context);

    <T extends UiTestComponent, D extends UiTestComponent> HtmlDefinitionListComponent<T, D> definitionListComponent(UiTestComponentContext context, Function<UiTestComponentContext, T> titleConstructor, Function<UiTestComponentContext, D> descriptionConstructor);

    HtmlImageComponent imageComponent(UiTestComponentContext context);

    default <T extends UiTestComponent> HtmlTableComponent<T> tableComponent(UiTestComponentContext context, Function<UiTestComponentContext, T> entryConstructor) {
        return new HtmlTableComponent<>(context, entryConstructor);
    }
}
