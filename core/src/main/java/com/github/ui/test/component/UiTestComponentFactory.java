package com.github.ui.test.component;

import com.github.ui.test.component.playwright.PlaywrightComponentFactory;
import com.github.ui.test.context.UiTestComponentContext;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.function.Function;

@Getter
@RequiredArgsConstructor
public class UiTestComponentFactory {

    private static final PlaywrightComponentFactory factory = new PlaywrightComponentFactory();

    public static Function<UiTestComponentContext, HtmlInputComponent> inputComponent() {
        return factory::inputComponent;
    }

    public static Function<UiTestComponentContext, HtmlCheckboxComponent> checkboxComponent() {
        return factory::checkboxComponent;
    }

    public static Function<UiTestComponentContext, HtmlTextAreaComponent> textAreaComponent() {
        return factory::textAreaComponent;
    }

    public static Function<UiTestComponentContext, UiTestComponent> buttonComponent() {
        return factory::buttonComponent;
    }

    public static Function<UiTestComponentContext, HtmlSelectComponent> selectComponent() {
        return factory::selectComponent;
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
        return context -> factory.definitionListComponent(context, titleConstructor, descriptionConstructor);
    }

    public static <T extends UiTestComponent> Function<UiTestComponentContext, UiTestTableComponent<T>> tableComponent(Function<UiTestComponentContext, T> entryConstructor) {
        return context -> new UiTestTableComponent<>(context, entryConstructor);
    }
}
