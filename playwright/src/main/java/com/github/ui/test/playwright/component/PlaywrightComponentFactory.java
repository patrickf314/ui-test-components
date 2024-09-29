package com.github.ui.test.playwright.component;

import com.github.ui.test.core.component.*;
import com.github.ui.test.core.context.UiTestComponentContext;
import com.github.ui.test.playwright.context.PlaywrightComponentContext;
import com.github.ui.test.core.exception.IllegalContextException;

import java.util.function.Function;

public class PlaywrightComponentFactory implements UiTestComponentFactory {

    @Override
    public HtmlInputComponent inputComponent(UiTestComponentContext context) {
        return new PlaywrightInputComponent(context);
    }

    @Override
    public HtmlCheckboxComponent checkboxComponent(UiTestComponentContext context) {
        return new PlaywrightCheckboxComponent(context);
    }

    @Override
    public HtmlTextAreaComponent textAreaComponent(UiTestComponentContext context) {
        return new PlaywrightTextAreaComponent(context);
    }

    @Override
    public UiTestComponent buttonComponent(UiTestComponentContext context) {
        return new PlaywrightButtonComponent(context);
    }

    @Override
    public HtmlSelectComponent selectComponent(UiTestComponentContext context) {
        return new PlaywrightSelectComponent(context);
    }

    @Override
    public HtmlFileInputComponent fileInputComponent(UiTestComponentContext context) {
        return new PlaywrightFileInputComponent(context);
    }

    @Override
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
