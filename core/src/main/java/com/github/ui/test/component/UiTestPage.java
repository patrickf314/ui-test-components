package com.github.ui.test.component;

import com.github.ui.test.context.UiTestPageContext;
import lombok.Getter;

import java.util.function.Function;

@Getter
public class UiTestPage extends UiTestElement<UiTestPageContext> {

    private final String pathPattern;

    public UiTestPage(UiTestPageContext context, String pathPattern) {
        super(context);

        this.pathPattern = pathPattern;
    }

    public void close() {
        getContext().close();
    }

    public <T extends UiTestPage> T reload(Function<UiTestPageContext, T> constructor) {
        return getContext().reload(constructor);
    }
}
