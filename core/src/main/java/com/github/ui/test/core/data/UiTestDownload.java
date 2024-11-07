package com.github.ui.test.core.data;

import java.io.InputStream;

public interface UiTestDownload {

    InputStream openInputStream();

    String getSuggestedFileName();

    default void saveIn(String directory) {
        saveIn(directory, getSuggestedFileName());
    }


    void saveIn(String directory, String fileName);

    void delete();
}
