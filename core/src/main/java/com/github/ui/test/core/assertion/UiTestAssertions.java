package com.github.ui.test.core.assertion;

import com.github.ui.test.core.component.*;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.assertj.core.api.Assertions;

import static com.github.ui.test.core.UiTestEnvironment.getEnvironment;

/**
 * Extension of the assertj {@link Assertions} for {@link UiTestComponent}s.
 */
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UiTestAssertions extends Assertions {

    /**
     * Basic assert for a {@link UiTestComponent}
     *
     * @param actual the actual component
     * @param <T>    the type of the component
     * @return the component assert
     */
    public static <T extends UiTestComponent> UiTestComponentAssert<T> assertThat(T actual) {
        return getEnvironment().getAssertFactory().componentAssert(actual);
    }

    /**
     * List assert for a {@link UiTestComponentList}
     *
     * @param actual the actual component list
     * @param <T>    the type of the list items
     * @return the component list assert
     */
    public static <T extends UiTestComponent> UiTestListComponentAssert<T> assertThat(UiTestComponentList<T> actual) {
        return getEnvironment().getAssertFactory().listComponentAssert(actual);
    }

    /**
     * Assert for a {@link HtmlButtonComponent}
     *
     * @param actual the actual button component
     * @return the button component assert
     */
    public static HtmlButtonComponentAssert assertThat(HtmlButtonComponent actual) {
        return getEnvironment().getAssertFactory().htmlButtonComponentAssert(actual);
    }

    /**
     * Assert for a {@link HtmlCheckboxComponent}
     *
     * @param actual the actual checkbox component
     * @return the checkbox component assert
     */
    public static HtmlCheckboxComponentAssert assertThat(HtmlCheckboxComponent actual) {
        return getEnvironment().getAssertFactory().htmlCheckboxComponentAssert(actual);
    }

    /**
     * Assert for a {@link HtmlDefinitionListComponentAssert}
     *
     * @param actual the actual HTML definition list component
     * @return the HTML definition list component assert
     */
    public static <T extends UiTestComponent, D extends UiTestComponent> HtmlDefinitionListComponentAssert<T, D> assertThat(HtmlDefinitionListComponent<T, D> actual) {
        return getEnvironment().getAssertFactory().htmlDefinitionListComponentAssert(actual);
    }

    /**
     * Assert for a {@link HtmlInputComponent}
     *
     * @param actual the actual input component
     * @return the input component assert
     */
    public static HtmlInputComponentAssert assertThat(HtmlInputComponent actual) {
        return getEnvironment().getAssertFactory().htmlInputComponentAssert(actual);
    }

    /**
     * Assert for a {@link HtmlSelectComponent}
     *
     * @param actual the actual select component
     * @return the select component assert
     */
    public static HtmlSelectComponentAssert assertThat(HtmlSelectComponent actual) {
        return getEnvironment().getAssertFactory().htmlSelectComponentAssert(actual);
    }
}
