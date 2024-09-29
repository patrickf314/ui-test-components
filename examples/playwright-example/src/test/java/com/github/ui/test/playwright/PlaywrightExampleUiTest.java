package com.github.ui.test.playwright;

import com.github.ui.test.core.UiTestEnvironment;
import com.github.ui.test.core.browser.UiTestBrowser;
import com.github.ui.test.core.context.UiTestContext;
import com.github.ui.test.playwright.browser.PlaywrightBrowserType;
import com.github.ui.test.playwright.page.TestPage;
import org.junit.jupiter.api.*;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;
import org.testcontainers.utility.MountableFile;

import java.util.UUID;

import static com.github.ui.test.core.assertion.UiTestAssertions.assertThat;

@Testcontainers
class PlaywrightExampleUiTest {

    private static final PlaywrightBrowserType BROWSER_TYPE = PlaywrightBrowserType.FIREFOX;

    @Container
    private final GenericContainer<?> webserverContainer = new GenericContainer<>(DockerImageName.parse("httpd:2.4.62"))
            .withCopyFileToContainer(MountableFile.forClasspathResource("test.html"), "/usr/local/apache2/htdocs/")
            .withExposedPorts(80);

    private static UiTestBrowser browser;
    private UiTestContext context;

    @BeforeAll
    static void setupPlaywrightEnvironment() {
        PlaywrightUiTestEnvironment.setup();
        browser = BROWSER_TYPE.startBrowser();
    }

    @AfterAll
    static void stopBrowser() {
        browser.stop();
        UiTestEnvironment.getEnvironment().close();
    }

    @BeforeEach
    void setupTest(TestInfo testInfo) {
        context = browser.createNewTestContext("http://localhost:" + webserverContainer.getMappedPort(80), "target/ui-tests", testName(testInfo));
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
