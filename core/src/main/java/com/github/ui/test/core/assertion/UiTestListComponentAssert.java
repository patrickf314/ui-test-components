package com.github.ui.test.core.assertion;

import com.github.ui.test.core.component.UiTestComponent;
import com.github.ui.test.core.predicate.UiTestComponentPredicate;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

import static com.github.ui.test.core.predicate.UiTestComponentPredicate.anyOf;

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

    default UiTestListComponentAssert<T> doesNotContainElementWithText(String text) {
        return doesNotContain(UiTestComponentPredicate.hasText(text));
    }

    default UiTestListComponentAssert<T> doesNotContain(List<UiTestComponentPredicate> predicates) {
        return doesNotContain(anyOf(predicates));
    }

    default UiTestListComponentAssert<T> doesNotContain(UiTestComponentPredicate predicate, UiTestComponentPredicate... otherPredicates) {
        return containsOnly(anyOf(predicate, otherPredicates).negate());
    }

    UiTestComponentAssert first();

    <S extends UiTestComponent> UiTestListComponentAssert<S> extracting(Function<T, S> mapper);
}
