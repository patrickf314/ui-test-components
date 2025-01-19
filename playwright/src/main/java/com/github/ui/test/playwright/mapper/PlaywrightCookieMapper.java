package com.github.ui.test.playwright.mapper;

import com.github.ui.test.core.data.UiTestCookie;
import com.microsoft.playwright.options.Cookie;
import org.mapstruct.Mapper;

@Mapper
public interface PlaywrightCookieMapper {

    UiTestCookie mapCookie(Cookie cookie);

}
