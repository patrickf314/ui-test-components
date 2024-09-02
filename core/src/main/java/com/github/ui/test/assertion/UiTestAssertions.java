package com.github.ui.test.assertion;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.assertj.core.api.Assertions;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UiTestAssertions extends Assertions {

    public static <T extends UiTestComponent> PlaywrightComponentAssert<T> assertThat(T actual) {
        return new PlaywrightComponentAssert<>(actual);
    }

    public static <T extends UiTestComponent> PlaywrightListComponentAssert<T> assertThat(UiTestComponentList<T> actual) {
        return new PlaywrightListComponentAssert<>(actual);
    }

    public static <T extends HtmlSelectComponent> PlaywrightSelectComponentAssert<T> assertThat(T actual) {
        return new PlaywrightSelectComponentAssert<>(actual);
    }

    public static <T extends UiTestComponent, D extends UiTestComponent> PlaywrightDefinitionListComponentAssert<T, D> assertThat(DefinitionListComponent<T, D> actual) {
        return new PlaywrightDefinitionListComponentAssert<>(actual);
    }
}
