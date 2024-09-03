package com.github.ui.test.core.context;

import com.github.ui.test.core.component.UiTestPage;

import java.util.function.Function;

public interface UiTestPageContext extends UiTestElementContext {

    void close();

    String getUrl();

    <T extends UiTestPage> T reload(Function<UiTestPageContext, T> constructor);
}
