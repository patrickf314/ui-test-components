package com.github.ui.test.context.playwright;

import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.Tracing;
import com.github.ui.test.component.UiTestPage;
import com.github.ui.test.context.UiTestContext;
import com.github.ui.test.context.UiTestPageContext;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;

import java.io.File;
import java.nio.file.Paths;
import java.util.function.Function;

@Slf4j
@Getter
@RequiredArgsConstructor
public class PlaywrightContext implements UiTestContext {

    private final String baseUrl;
    private final String outputDirectory;
    private final String testName;
    private final BrowserContext browserContext;

    @Override
    public void close() {
        log.info("Closing test context for {} finished", testName);
        MDC.remove("traceId");

        browserContext.tracing().stop(new Tracing.StopOptions()
                .setPath(Paths.get(outputDirectory, "traces", testName.replace('.', File.separatorChar) + ".zip"))
        );

        browserContext.close();
    }

    @Override
    public <T extends UiTestPage> T openNewPage(Function<UiTestPageContext, T> pageConstructor, String path) {
        var page = browserContext.newPage();
        page.navigate(baseUrl + path);
        var pageContext = new PlaywrightPageContext(baseUrl, page);
        return pageContext.waitForPage(pageConstructor);
    }
}
