package com.github.ui.test.component;

import com.github.ui.test.context.UiTestComponentContext;
import com.github.ui.test.predicate.UiTestComponentPredicate;

import java.util.function.Function;

public interface UiTestComponentList<T extends UiTestComponent> {

    UiTestComponentContext getContext();

    T get(int index);

    <S extends UiTestComponent> UiTestComponentList<S> map(Function<T, S> mapper);

    UiTestComponentList<T> filter(UiTestComponentPredicate predicate);

    default T first(UiTestComponentPredicate predicate) {
        return filter(predicate).get(0);
    }

    T singleton();
}
