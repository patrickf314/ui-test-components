package com.github.ui.test.core.browser;

import com.github.ui.test.core.context.UiTestContext;

public interface UiTestBrowser {

    default UiTestContext createNewTestContext(String outputDirectory, String testName) {
        return createNewTestContext(outputDirectory, testName, null);
    }

    UiTestContext createNewTestContext(String outputDirectory, String testName, Object properties);

    void stop();
}
