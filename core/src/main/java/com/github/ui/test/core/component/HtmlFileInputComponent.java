package com.github.ui.test.core.component;

import com.github.ui.test.core.context.UiTestComponentContext;

import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public abstract class HtmlFileInputComponent extends UiTestComponent {

    protected HtmlFileInputComponent(UiTestComponentContext context) {
        super(context);
    }

    public void selectFile(URL file) {
        try {
            selectFile(Paths.get(file.toURI()));
        } catch (URISyntaxException e) {
            throw new IllegalArgumentException("Failed to convert file URL to path", e);
        }
    }

    public abstract void selectFile(Path file);

    public abstract void selectFiles(List<Path> files);
}
