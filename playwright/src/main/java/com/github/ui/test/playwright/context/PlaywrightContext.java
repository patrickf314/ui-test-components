package com.github.ui.test.playwright.context;

import com.github.ui.test.core.component.UiTestPage;
import com.github.ui.test.core.context.UiTestContext;
import com.github.ui.test.core.context.UiTestPageContext;
import com.github.ui.test.core.data.UiTestCookie;
import com.github.ui.test.playwright.PlaywrightUiTestEnvironment;
import com.github.ui.test.playwright.mapper.PlaywrightCookieMapper;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.Tracing;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mapstruct.factory.Mappers;

import java.io.File;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.List;
import java.util.function.Function;

@Slf4j
@Getter
@RequiredArgsConstructor
public class PlaywrightContext implements UiTestContext {

    private static final PlaywrightCookieMapper COOKIE_MAPPER = Mappers.getMapper(PlaywrightCookieMapper.class);

    private final Duration defaultTimeout;
    private final String baseUrl;
    private final String outputDirectory;
    private final String testName;
    private final BrowserContext browserContext;

    @Override
    public void close() {
        log.info("Closing test context for {}", testName);

        if (!PlaywrightUiTestEnvironment.getEnvironment().getOptions().isTracesDisabled()) {
            browserContext.tracing().stop(new Tracing.StopOptions()
                    .setPath(Paths.get(outputDirectory, "traces", testName.replace('.', File.separatorChar) + ".zip"))
            );
        }

        browserContext.close();
    }

    @Override
    public <T extends UiTestPage> T openNewPage(Function<UiTestPageContext, T> pageConstructor, String path) {
        var page = browserContext.newPage();
        page.setDefaultTimeout(defaultTimeout.toMillis());
        page.navigate(baseUrl + path);
        var pageContext = new PlaywrightPageContext(baseUrl, page);
        return pageContext.waitForPage(pageConstructor);
    }

    @Override
    public List<UiTestCookie> getCookies() {
        return browserContext.cookies()
                .stream()
                .map(COOKIE_MAPPER::mapCookie)
                .toList();
    }

    @Override
    public void clearCookies() {
        browserContext.clearCookies();
    }
}
