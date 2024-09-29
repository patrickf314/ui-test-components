package com.github.ui.test.core.assertion;

import com.github.ui.test.core.component.*;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.assertj.core.api.Assertions;

import static com.github.ui.test.core.UiTestEnvironment.getEnvironment;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UiTestAssertions extends Assertions {

    public static <T extends UiTestComponent> UiTestComponentAssert<T> assertThat(T actual) {
        return getEnvironment().getAssertFactory().componentAssert(actual);
    }

    public static <T extends UiTestComponent> UiTestListComponentAssert<T> assertThat(UiTestComponentList<T> actual) {
        return getEnvironment().getAssertFactory().listComponentAssert(actual);
    }

    public static <T extends HtmlInputComponent> HtmlInputComponentAssert<T> assertThat(T actual) {
        return getEnvironment().getAssertFactory().htmlInputComponentAssert(actual);
    }

    public static <T extends HtmlSelectComponent> HtmlSelectComponentAssert<T> assertThat(T actual) {
        return getEnvironment().getAssertFactory().htmlSelectComponentAssert(actual);
    }

    public static <T extends UiTestComponent, D extends UiTestComponent> HtmlDefinitionListComponentAssert<T, D> assertThat(DefinitionListComponent<T, D> actual) {
        return getEnvironment().getAssertFactory().htmlDefinitionListComponentAssert(actual);
    }
}
