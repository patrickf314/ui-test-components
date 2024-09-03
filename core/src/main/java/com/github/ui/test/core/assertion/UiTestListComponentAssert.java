package com.github.ui.test.core.assertion;

import com.github.ui.test.core.UiTestEnvironment;
import com.github.ui.test.core.component.UiTestComponent;
import com.github.ui.test.core.component.UiTestComponentList;
import com.github.ui.test.core.predicate.UiTestComponentPredicate;
import org.assertj.core.api.Assert;

import java.util.Arrays;
import java.util.List;

import static com.github.ui.test.core.UiTestEnvironment.hasText;

public interface UiTestListComponentAssert<SELF extends UiTestListComponentAssert<SELF, ITEM>, ITEM extends UiTestComponent>
        extends Assert<SELF, UiTestComponentList<ITEM>> {

    SELF hasSize(int size);

    default SELF isEmpty() {
        return hasSize(0);
    }

    SELF isNotEmpty();

    default SELF containsElementWithText(String text) {
        return contains(hasText(text));
    }

    SELF contains(UiTestComponentPredicate predicate);

    SELF containsOnly(UiTestComponentPredicate predicate);

    default SELF containsExactlyElementsWithText(String... itemTexts) {
        return containsExactlyElementsWithText(Arrays.asList(itemTexts));
    }

    default SELF containsExactlyElementsWithText(List<String> itemTexts) {
        return containsExactly(itemTexts.stream()
                .map(UiTestEnvironment::hasText)
                .toList()
        );
    }

    default SELF containsExactly(UiTestComponentPredicate... itemPredicates) {
        return containsExactly(Arrays.asList(itemPredicates));
    }

    SELF containsExactly(List<UiTestComponentPredicate> itemPredicates);

    default SELF doesNotContain(UiTestComponentPredicate predicate) {
        return containsOnly(predicate.negate());
    }
}
