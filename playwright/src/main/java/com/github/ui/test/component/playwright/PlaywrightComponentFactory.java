package com.github.ui.test.component.playwright;

import java.util.function.Function;

public class PlaywrightComponentFactory {

    public HtmlInputComponent inputComponent(UiTestComponentContext context) {
        return new PlaywrightInputComponent(context);
    }

    public HtmlCheckboxComponent checkboxComponent(UiTestComponentContext context) {
        return new PlaywrightCheckboxComponent(context);
    }

    public HtmlTextAreaComponent textAreaComponent(UiTestComponentContext context) {
        return new PlaywrightTextAreaComponent(context);
    }

    public UiTestComponent buttonComponent(UiTestComponentContext context) {
        return new PlaywrightButtonComponent(context);
    }

    public HtmlSelectComponent selectComponent(UiTestComponentContext context) {
        return new PlaywrightSelectComponent(context);
    }

    public <T extends UiTestComponent, D extends UiTestComponent> DefinitionListComponent<T, D> definitionListComponent(UiTestComponentContext context, Function<UiTestComponentContext, T> titleConstructor, Function<UiTestComponentContext, D> descriptionConstructor) {
        return new PlaywrightDefinitionListComponent<>(context, titleConstructor, descriptionConstructor);
    }

    public static PlaywrightComponentContext requirePlaywrightContext(UiTestComponentContext context) {
        if (context instanceof PlaywrightComponentContext playwrightContext) {
            return playwrightContext;
        }

        throw new IllegalContextException("playwright", "context of type " + context.getClass().getName());
    }
}
