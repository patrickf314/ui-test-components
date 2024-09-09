package com.github.ui.test.core.data;

import java.io.InputStream;

public interface UiTestDownload {

    InputStream openInputStream();

    void saveIn(String directory);

    void saveIn(String directory, String fileName);

    void delete();
}
