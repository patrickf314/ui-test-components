package com.github.ui.test.core.assertion;

import com.github.ui.test.core.component.HtmlImageComponent;

/**
 * Extension of the {@link GenericUiTestComponentAssert} for {@link HtmlImageComponent}
 */
public interface HtmlImageComponentAssert extends GenericUiTestComponentAssert<HtmlImageComponentAssert, HtmlImageComponent> {

    /**
     * Checks if the image was loaded successfully, i.e.
     * if the browser shows the image.
     *
     * @return this
     */
    HtmlImageComponentAssert isLoaded();

    /**
     * Checks if the image is loaded, but the source does not exist
     * or is invalid.
     * In this case, the browser renders the alternate text.
     *
     * @return this
     */
    HtmlImageComponentAssert hasFailedLoading();

    /**
     * Checks if the image has the given alternate text.
     * This function is equivalent to {@link #hasAttribute(String, String)}}
     * for the 'alt' attribute.
     *
     * @param text the expected alternate text
     * @return this
     */
    default HtmlImageComponentAssert hasAlternateText(String text) {
        return hasAttribute("alt", text);
    }

    /**
     * Checks if the image has the given source.
     * This function is equivalent to {@link #hasAttribute(String, String)}}
     * for the 'src' attribute.
     *
     * @param source the expected source
     * @return this
     */
    default HtmlImageComponentAssert hasSource(String source) {
        return hasAttribute("src", source);
    }
}
