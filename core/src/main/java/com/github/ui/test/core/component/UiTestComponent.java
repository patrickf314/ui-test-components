package com.github.ui.test.core.component;

import com.github.ui.test.core.action.UiTestCursorAction;
import com.github.ui.test.core.context.UiTestComponentContext;

import java.util.function.Function;

import static com.github.ui.test.core.UiTestEnvironment.getEnvironment;

/**
 * The base class for ui-test components.
 * Each component represents an HTML element in the DOM.
 *
 * @see UiTestComponentList
 */
public class UiTestComponent extends UiTestElement<UiTestComponentContext> {

    /**
     * Constructor
     *
     * @param context the component context
     */
    public UiTestComponent(UiTestComponentContext context) {
        super(context);
    }

    /**
     * Constructing function of an {@link HtmlInputComponent}
     *
     * @return the component constructor
     */
    public static Function<UiTestComponentContext, HtmlInputComponent> inputComponent() {
        return getEnvironment().getComponentFactory()::inputComponent;
    }

    /**
     * Constructing function of an {@link HtmlCheckboxComponent}
     *
     * @return the component constructor
     */
    public static Function<UiTestComponentContext, HtmlCheckboxComponent> checkboxComponent() {
        return getEnvironment().getComponentFactory()::checkboxComponent;
    }

    /**
     * Constructing function of an {@link HtmlTextAreaComponent}
     *
     * @return the component constructor
     */
    public static Function<UiTestComponentContext, HtmlTextAreaComponent> textAreaComponent() {
        return getEnvironment().getComponentFactory()::textAreaComponent;
    }

    /**
     * Constructing function of an {@link HtmlButtonComponent}
     *
     * @return the component constructor
     */
    public static Function<UiTestComponentContext, HtmlButtonComponent> buttonComponent() {
        return getEnvironment().getComponentFactory()::buttonComponent;
    }

    /**
     * Constructing function of an {@link HtmlAnchorComponent}
     *
     * @return the component constructor
     */
    public static Function<UiTestComponentContext, HtmlAnchorComponent> anchorComponent() {
        return getEnvironment().getComponentFactory()::anchorComponent;
    }

    /**
     * Constructing function of an {@link HtmlSelectComponent}
     *
     * @return the component constructor
     */
    public static Function<UiTestComponentContext, HtmlSelectComponent> selectComponent() {
        return getEnvironment().getComponentFactory()::selectComponent;
    }

    /**
     * Constructing function of an {@link HtmlFileInputComponent}
     *
     * @return the component constructor
     */
    public static Function<UiTestComponentContext, HtmlFileInputComponent> fileInputComponent() {
        return getEnvironment().getComponentFactory()::fileInputComponent;
    }

    /**
     * Constructing function of an {@link HtmlDefinitionListComponent}
     *
     * @return the component constructor
     */
    public static Function<UiTestComponentContext, HtmlDefinitionListComponent<UiTestComponent, UiTestComponent>> definitionList() {
        return definitionList(UiTestComponent::new);
    }

    /**
     * Constructing function of an {@link HtmlDefinitionListComponent}
     *
     * @param descriptionConstructor the constructing function of the description component
     * @param <D>                    the description component type
     * @return the component constructor
     */
    public static <D extends UiTestComponent> Function<UiTestComponentContext, HtmlDefinitionListComponent<UiTestComponent, D>> definitionList(
            Function<UiTestComponentContext, D> descriptionConstructor) {
        return definitionList(UiTestComponent::new, descriptionConstructor);
    }

    /**
     * Constructing function of an {@link HtmlDefinitionListComponent}
     *
     * @param titleConstructor       the constructing function of the title component
     * @param descriptionConstructor the constructing function of the description component
     * @param <T>                    the title component type
     * @param <D>                    the description component type
     * @return the component constructor
     */
    public static <T extends UiTestComponent, D extends UiTestComponent> Function<UiTestComponentContext, HtmlDefinitionListComponent<T, D>> definitionList(
            Function<UiTestComponentContext, T> titleConstructor,
            Function<UiTestComponentContext, D> descriptionConstructor) {
        return context -> getEnvironment().getComponentFactory().definitionListComponent(context, titleConstructor, descriptionConstructor);
    }

    public static <T extends UiTestComponent> Function<UiTestComponentContext, HtmlTableComponent<T>> tableComponent(Function<UiTestComponentContext, T> entryConstructor) {
        return context -> getEnvironment().getComponentFactory().tableComponent(context, entryConstructor);
    }

    /**
     * Clicks this component
     */
    public void click() {
        getContext().click();
    }

    /**
     * Getter for the {@link UiTestCursorAction} of this component
     *
     * @return the cursor action
     */
    public UiTestCursorAction cursor() {
        return getContext().cursor();
    }
}
