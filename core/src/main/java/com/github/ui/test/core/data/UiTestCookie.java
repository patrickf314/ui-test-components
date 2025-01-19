package com.github.ui.test.core.data;

import lombok.Data;

@Data
public class UiTestCookie {

    private String name;
    private String value;
    private String url;
    private String domain;
    private String path;
    private Double expires;
    private Boolean httpOnly;
    private Boolean secure;
    private SameSiteAttribute sameSite;

    public enum SameSiteAttribute {
        STRICT, LAX, NONE
    }
}
