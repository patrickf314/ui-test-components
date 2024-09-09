package com.github.ui.test.playwright.data;

import com.github.ui.test.core.data.UiTestDownload;
import com.microsoft.playwright.Download;
import lombok.RequiredArgsConstructor;

import java.io.InputStream;
import java.nio.file.Paths;

@RequiredArgsConstructor
public class PlaywrightDownload implements UiTestDownload {

    private final Download download;

    @Override
    public InputStream openInputStream() {
        return download.createReadStream();
    }

    @Override
    public void saveIn(String directory) {
        saveIn(directory, download.suggestedFilename());
    }

    @Override
    public void saveIn(String directory, String fileName) {
        download.saveAs(Paths.get(directory, fileName));
    }

    @Override
    public void delete() {
        download.delete();
    }
}
