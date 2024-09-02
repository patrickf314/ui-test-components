package com.github.ui.test.context;

import com.github.ui.test.component.UiTestPage;

import java.util.function.Function;

public interface UiTestPageContext extends UiTestElementContext {

    void close();

    String getUrl();

    <T extends UiTestPage> T reload(Function<UiTestPageContext, T> constructor);
}
