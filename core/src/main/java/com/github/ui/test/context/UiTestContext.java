package com.github.ui.test.context;

import com.github.ui.test.component.UiTestPage;

import java.util.function.Function;

public interface UiTestContext {

    String getTestName();

    void close();

    <T extends UiTestPage> T openNewPage(Function<UiTestPageContext, T> pageConstructor, String url);
}
