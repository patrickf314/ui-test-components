package com.github.ui.test.core.browser;

import com.github.ui.test.core.context.UiTestContext;

public interface UiTestBrowser {

    void start();

    UiTestContext createNewTestContext(String baseUrl, String outputDirectory, String testName);

    void stop();
}
