package com.github.ui.test.core.component;

import com.github.ui.test.core.context.UiTestPageContext;
import lombok.Getter;

import java.util.Map;
import java.util.function.Function;

@Getter
public abstract class UiTestPage extends UiTestElement<UiTestPageContext> {

    public UiTestPage(UiTestPageContext context) {
        super(context);
    }

    public void close() {
        getContext().close();
    }

    public <T extends UiTestPage> T reload(Function<UiTestPageContext, T> constructor) {
        return getContext().reload(constructor);
    }

    public <T extends UiTestPage> T navigateTo(Function<UiTestPageContext, T> constructor, String path) {
        return getContext().navigateTo(constructor, path);
    }

    public abstract boolean matchesUrl(UiTestPageContext.Url url);
}
