package com.github.ui.test.core;

import com.github.ui.test.core.browser.UiTestBrowser;
import com.github.ui.test.core.component.*;
import com.github.ui.test.core.context.UiTestComponentContext;
import com.github.ui.test.core.context.UiTestContext;
import com.github.ui.test.core.predicate.UiTestComponentPredicateFactory;
import com.github.ui.test.core.selector.SelectorFactory;

import java.util.function.Function;

public abstract class UiTestEnvironment {

    private static UiTestEnvironment instance;

    public static UiTestEnvironment getEnvironment() {
        if (instance == null) {
            throw new IllegalStateException("UiTestEnvironment has not been registered.");
        }

        return instance;
    }

    public static void registerEnvironment(UiTestEnvironment environment) {
        if (instance != null) {
            throw new IllegalStateException("UiTestEnvironment has already been registered.");
        }

        instance = environment;
    }

    public static UiTestContext createNewTestContext(String baseUrl, String outputDirectory, String testName) {
        return getEnvironment().getBrowser().createNewTestContext(baseUrl, outputDirectory, testName);
    }

    // #################################################################################################################
    // # Components
    // #################################################################################################################

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

    // #################################################################################################################
    // # Predicates
    // #################################################################################################################

    public abstract UiTestBrowser getBrowser();

    public abstract UiTestComponentFactory getComponentFactory();

    public abstract UiTestComponentPredicateFactory getPredicateFactory();

    public abstract SelectorFactory getSelectorFactory();

}
