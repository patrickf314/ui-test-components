package com.github.ui.test.component.playwright;

import com.github.ui.test.component.UiTestComponent;
import com.github.ui.test.component.UiTestComponentList;
import com.github.ui.test.context.UiTestComponentContext;
import com.github.ui.test.context.playwright.PlaywrightComponentContext;
import com.github.ui.test.predicate.UiTestComponentPredicate;
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
