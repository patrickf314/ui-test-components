package com.github.ui.test.core.component;

import com.github.ui.test.core.context.UiTestComponentContext;

import java.util.function.Function;

import static com.github.ui.test.core.UiTestEnvironment.getEnvironment;

public class UiTestComponent extends UiTestElement<UiTestComponentContext> {

    public UiTestComponent(UiTestComponentContext context) {
        super(context);
    }

    public static Function<UiTestComponentContext, HtmlInputComponent> inputComponent() {
        return getEnvironment().getComponentFactory()::inputComponent;
    }

    public static Function<UiTestComponentContext, HtmlCheckboxComponent> checkboxComponent() {
        return getEnvironment().getComponentFactory()::checkboxComponent;
    }

    public static Function<UiTestComponentContext, HtmlTextAreaComponent> textAreaComponent() {
        return getEnvironment().getComponentFactory()::textAreaComponent;
    }

    public static Function<UiTestComponentContext, UiTestComponent> buttonComponent() {
        return getEnvironment().getComponentFactory()::buttonComponent;
    }

    public static Function<UiTestComponentContext, HtmlSelectComponent> selectComponent() {
        return getEnvironment().getComponentFactory()::selectComponent;
    }

    public static Function<UiTestComponentContext, HtmlFileInputComponent> fileInputComponent() {
        return getEnvironment().getComponentFactory()::fileInputComponent;
    }

    public static Function<UiTestComponentContext, DefinitionListComponent<UiTestComponent, UiTestComponent>> definitionList() {
        return definitionList(UiTestComponent::new);
    }

    public static <D extends UiTestComponent> Function<UiTestComponentContext, DefinitionListComponent<UiTestComponent, D>> definitionList(
            Function<UiTestComponentContext, D> descriptionConstructor) {
        return definitionList(UiTestComponent::new, descriptionConstructor);
    }

    public static <T extends UiTestComponent, D extends UiTestComponent> Function<UiTestComponentContext, DefinitionListComponent<T, D>> definitionList(
            Function<UiTestComponentContext, T> titleConstructor,
            Function<UiTestComponentContext, D> descriptionConstructor) {
        return context -> getEnvironment().getComponentFactory().definitionListComponent(context, titleConstructor, descriptionConstructor);
    }

    public static <T extends UiTestComponent> Function<UiTestComponentContext, UiTestTableComponent<T>> tableComponent(Function<UiTestComponentContext, T> entryConstructor) {
        return context -> getEnvironment().getComponentFactory().tableComponent(context, entryConstructor);
    }

    public void click() {
        getContext().click();
    }
}
