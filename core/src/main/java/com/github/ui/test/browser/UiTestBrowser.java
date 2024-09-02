package com.github.ui.test.browser;

import com.github.ui.test.context.UiTestContext;

public interface UiTestBrowser {

    void start();

    UiTestContext createNewTestContext(String baseUrl, String outputDirectory, String testName);

    void stop();
}
