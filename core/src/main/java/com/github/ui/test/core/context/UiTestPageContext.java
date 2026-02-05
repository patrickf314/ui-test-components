package com.github.ui.test.core.context;

import com.github.ui.test.core.component.UiTestPage;

import java.util.Map;
import java.util.function.Function;

public interface UiTestPageContext extends UiTestElementContext {

    void close();

    Url getUrl();

    <T extends UiTestPage> T reload(Function<UiTestPageContext, T> constructor);

    <T extends UiTestPage> T navigateTo(Function<UiTestPageContext, T> constructor, String path);

    record Url(String baseUrl, String path, Map<String, String> queryParameters, String anchor) {

        @Override
        public String toString() {
            return baseUrl +
                    (path != null ? path : "") +
                    (queryParameters != null && !queryParameters.isEmpty() ? "?" + queryParameters.entrySet().stream()
                            .map(entry -> entry.getKey() + "=" + entry.getValue())
                            .reduce((a, b) -> a + "&" + b).orElse("") : "") +
                    (anchor != null ? "#" + anchor : "");
        }
    }
}
