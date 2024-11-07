package com.github.ui.test.playwright.component;

import com.github.ui.test.core.component.HtmlFileInputComponent;
import com.github.ui.test.core.context.UiTestComponentContext;

import java.nio.file.Path;
import java.util.List;

import static com.github.ui.test.playwright.component.PlaywrightComponentFactory.requirePlaywrightContext;

public class PlaywrightFileInputComponent extends HtmlFileInputComponent {

    public PlaywrightFileInputComponent(UiTestComponentContext context) {
        super(context);
    }

    @Override
    public void selectFile(Path file) {
        var context = requirePlaywrightContext(getContext());

        context.getLocator().setInputFiles(file);
        context.evaluateScript("/js/waitUntilLoadingFinished.js");
    }

    @Override
    public void selectFiles(List<Path> files) {
        var context = requirePlaywrightContext(getContext());

        context.evaluateScript("/js/waitInputEditable.js");
        context.getLocator().setInputFiles(files.toArray(Path[]::new));
        context.evaluateScript("/js/waitUntilLoadingFinished.js");
    }
}
