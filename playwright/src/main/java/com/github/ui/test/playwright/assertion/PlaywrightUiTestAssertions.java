package com.github.ui.test.playwright.assertion;

import com.github.ui.test.core.component.DefinitionListComponent;
import com.github.ui.test.core.component.HtmlSelectComponent;
import com.github.ui.test.core.component.UiTestComponent;
import com.github.ui.test.core.component.UiTestComponentList;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.assertj.core.api.Assertions;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PlaywrightUiTestAssertions extends Assertions {

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
