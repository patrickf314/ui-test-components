package com.github.ui.test.playwright.component;

import com.github.ui.test.core.component.UiTestComponent;
import com.github.ui.test.core.component.UiTestComponentList;
import com.github.ui.test.core.context.UiTestComponentContext;
import com.github.ui.test.playwright.context.PlaywrightComponentContext;
import com.github.ui.test.core.predicate.UiTestComponentPredicate;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.function.Function;

@RequiredArgsConstructor
public class PlaywrightListComponent<T extends UiTestComponent> implements UiTestComponentList<T> {

    @Getter
    private final PlaywrightComponentContext context;
    private final Function<UiTestComponentContext, T> itemConstructor;

    @Override
    public <S extends UiTestComponent> UiTestComponentList<S> map(Function<T, S> mapper) {
        return new PlaywrightListComponent<>(context, itemConstructor.andThen(mapper));
    }

    @Override
    public T get(int index) {
        return context.nth(itemConstructor, index);
    }

    @Override
    public PlaywrightListComponent<T> filter(UiTestComponentPredicate predicate) {
        return context.filter(itemConstructor, predicate);
    }

    @Override
    public T singleton() {
        return itemConstructor.apply(context);
    }
}
