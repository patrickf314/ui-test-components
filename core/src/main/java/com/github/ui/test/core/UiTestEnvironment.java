package com.github.ui.test.core;

import com.github.ui.test.core.assertion.UiTestAssertFactory;
import com.github.ui.test.core.component.UiTestComponentFactory;
import com.github.ui.test.core.predicate.UiTestComponentPredicateFactory;
import com.github.ui.test.core.selector.SelectorFactory;

import java.util.function.Supplier;

public abstract class UiTestEnvironment implements AutoCloseable {

    private static UiTestEnvironment instance;

    public static UiTestEnvironment getEnvironment() {
        if (instance == null) {
            throw new IllegalStateException("UiTestEnvironment has not been registered.");
        }

        return instance;
    }

    protected static <T extends UiTestEnvironment> void ensureCorrectEnvironment(Class<T> environmentType, Supplier<T> environmentSupplier) {
        if (instance == null) {
            instance = environmentSupplier.get();
        }

        if (!environmentType.isInstance(instance)) {
            throw new IllegalStateException("A different UiTestEnvironment has been registered.");
        }
    }

    @Override
    public abstract void close();

    public abstract UiTestComponentFactory getComponentFactory();

    public abstract UiTestComponentPredicateFactory getPredicateFactory();

    public abstract SelectorFactory getSelectorFactory();

    public abstract UiTestAssertFactory getAssertFactory();

}
