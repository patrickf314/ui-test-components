package com.github.ui.test.core;

import com.github.ui.test.core.browser.UiTestBrowser;
import com.github.ui.test.core.component.*;
import com.github.ui.test.core.context.UiTestComponentContext;
import com.github.ui.test.core.context.UiTestContext;
import com.github.ui.test.core.predicate.UiTestComponentPredicate;
import com.github.ui.test.core.predicate.UiTestComponentPredicates;
import com.github.ui.test.core.selector.Selector;
import com.github.ui.test.core.selector.UiTestSelectors;

import java.util.function.Function;
import java.util.regex.Pattern;

public abstract class UiTestEnvironment {

    private static UiTestEnvironment instance;

    public static UiTestEnvironment getInstance() {
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
        return getInstance().getBrowser().createNewTestContext(baseUrl, outputDirectory, testName);
    }

    // #################################################################################################################
    // # Components
    // #################################################################################################################

    public static Function<UiTestComponentContext, HtmlInputComponent> inputComponent() {
        return getInstance().getComponentFactory()::inputComponent;
    }

    public static Function<UiTestComponentContext, HtmlCheckboxComponent> checkboxComponent() {
        return getInstance().getComponentFactory()::checkboxComponent;
    }

    public static Function<UiTestComponentContext, HtmlTextAreaComponent> textAreaComponent() {
        return getInstance().getComponentFactory()::textAreaComponent;
    }

    public static Function<UiTestComponentContext, UiTestComponent> buttonComponent() {
        return getInstance().getComponentFactory()::buttonComponent;
    }

    public static Function<UiTestComponentContext, HtmlSelectComponent> selectComponent() {
        return getInstance().getComponentFactory()::selectComponent;
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
        return context -> getInstance().getComponentFactory().definitionListComponent(context, titleConstructor, descriptionConstructor);
    }

    public static <T extends UiTestComponent> Function<UiTestComponentContext, UiTestTableComponent<T>> tableComponent(Function<UiTestComponentContext, T> entryConstructor) {
        return context -> getInstance().getComponentFactory().tableComponent(context, entryConstructor);
    }

    // #################################################################################################################
    // # Predicates
    // #################################################################################################################

    public static UiTestComponentPredicate isVisible() {
        return getInstance().getPredicates().isVisible();
    }

    public static UiTestComponentPredicate hasText(String text) {
        return getInstance().getPredicates().hasText(text);
    }

    public static UiTestComponentPredicate matches(Pattern pattern) {
        return getInstance().getPredicates().matches(pattern);
    }

    public static UiTestComponentPredicate hasChild(String label, Selector childSelector, UiTestComponentPredicate childPredicate) {
        return getInstance().getPredicates().hasChild(label, childSelector, childPredicate);
    }

    public static UiTestComponentPredicate allOf(UiTestComponentPredicate predicate, UiTestComponentPredicate... other) {
        return getInstance().getPredicates().allOf(predicate, other);
    }

    public static UiTestComponentPredicate anyOf(UiTestComponentPredicate predicate, UiTestComponentPredicate... other) {
        return getInstance().getPredicates().anyOf(predicate, other);
    }

    public static UiTestComponentPredicate noneOf(UiTestComponentPredicate predicate, UiTestComponentPredicate... other) {
        return getInstance().getPredicates().noneOf(predicate, other);
    }

    // #################################################################################################################
    // # Selectors
    // #################################################################################################################

    public static Selector byCSSSelector(String cssSelector) {
        return getInstance().getSelectors().byCSSSelector(cssSelector);
    }

    public static Selector byText(String text) {
        return getInstance().getSelectors().byText(text);
    }

    public static Selector byTestId(String testId) {
        return getInstance().getSelectors().byTestId(testId);
    }

    public static Selector byXPath(String xPath) {
        return getInstance().getSelectors().byXPath(xPath);
    }

    public static Selector chained(Selector selector, Selector... selectors) {
        return getInstance().getSelectors().chained(selector, selectors);
    }

    public abstract UiTestBrowser getBrowser();

    public abstract UiTestComponentFactory getComponentFactory();

    public abstract UiTestComponentPredicates getPredicates();

    public abstract UiTestSelectors getSelectors();

}
