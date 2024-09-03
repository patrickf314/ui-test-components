package com.github.ui.test.playwright;

import com.github.ui.test.core.UiTestEnvironment;
import com.github.ui.test.core.context.UiTestContext;
import com.github.ui.test.playwright.browser.PlaywrightBrowser;
import com.github.ui.test.playwright.page.TestPage;
import org.junit.jupiter.api.*;

import java.util.UUID;

import static com.github.ui.test.playwright.assertion.PlaywrightUiTestAssertions.assertThat;

public class PlaywrightExampleUiTest {

    private static final PlaywrightBrowser BROWSER = new PlaywrightBrowser();
    private UiTestContext context;

    @BeforeAll
    static void setupPlaywrightEnvironment() {
        BROWSER.start();

        UiTestEnvironment.registerEnvironment(new PlaywrightUiTestEnvironment(BROWSER));
    }

    @AfterAll
    static void stopBrowser() {
        BROWSER.stop();
    }

    @BeforeEach
    void setupTest(TestInfo testInfo) {
        context = BROWSER.createNewTestContext("http://localhost:8080", "target/ui-tests", testName(testInfo));
    }

    @AfterEach
    void tearDown() {
        context.close();
    }

    @Test
    void test() {
        var value = UUID.randomUUID().toString();

        var page = context.openNewPage(TestPage::new, "/test.html");

        page.getInputField().setValue(value);
        page.getSubmitButton().click();

        assertThat(page.getOutput()).hasText(value);
    }

    private String testName(TestInfo testInfo) {
        var testClass = testInfo.getTestClass().orElse(null);
        var testMethod = testInfo.getTestMethod().orElse(null);

        if (testClass == null || testMethod == null) {
            return testInfo.getDisplayName().replace("()", "");
        }

        return testClass.getName() + "." + testMethod.getName();
    }
}