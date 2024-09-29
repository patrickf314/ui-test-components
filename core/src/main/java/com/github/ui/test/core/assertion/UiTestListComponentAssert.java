package com.github.ui.test.core.assertion;

import com.github.ui.test.core.component.UiTestComponent;
import com.github.ui.test.core.predicate.UiTestComponentPredicate;

import java.util.Arrays;
import java.util.List;

public interface UiTestListComponentAssert<T extends UiTestComponent> {

    UiTestListComponentAssert<T> hasSize(int size);

    default UiTestListComponentAssert<T> isEmpty() {
        return hasSize(0);
    }

    UiTestListComponentAssert<T> isNotEmpty();

    default UiTestListComponentAssert<T> containsElementWithText(String text) {
        return contains(UiTestComponentPredicate.hasText(text));
    }

    UiTestListComponentAssert<T> contains(UiTestComponentPredicate predicate);

    UiTestListComponentAssert<T> containsOnly(UiTestComponentPredicate predicate);

    default UiTestListComponentAssert<T> containsExactlyElementsWithText(String... itemTexts) {
        return containsExactlyElementsWithText(Arrays.asList(itemTexts));
    }

    default UiTestListComponentAssert<T> containsExactlyElementsWithText(List<String> itemTexts) {
        return containsExactly(itemTexts.stream()
                .map(UiTestComponentPredicate::hasText)
                .toList()
        );
    }

    default UiTestListComponentAssert<T> containsExactly(UiTestComponentPredicate... itemPredicates) {
        return containsExactly(Arrays.asList(itemPredicates));
    }

    UiTestListComponentAssert<T> containsExactly(List<UiTestComponentPredicate> itemPredicates);

    default UiTestListComponentAssert<T> doesNotContain(UiTestComponentPredicate predicate) {
        return containsOnly(predicate.negate());
    }
}
