package com.github.ui.test.core.context;

import com.github.ui.test.core.component.UiTestPage;

import java.util.function.Function;

public interface UiTestContext {

    String getTestName();

    void close();

    <T extends UiTestPage> T openNewPage(Function<UiTestPageContext, T> pageConstructor, String path);
}
