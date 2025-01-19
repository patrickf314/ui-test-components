package com.github.ui.test.core;

import com.github.ui.test.core.assertion.UiTestAssertFactory;
import com.github.ui.test.core.component.UiTestComponentFactory;
import com.github.ui.test.core.predicate.UiTestComponentPredicateFactory;
import com.github.ui.test.core.selector.SelectorFactory;

import java.util.function.Supplier;

/**
 * A test environment contains all structures
 * provided by a specific framework for UI testing.
 * <p>
 * Choose a specific framework and initialize the singleton instance
 * with this implementation.
 * Then, all functions like of {@link com.github.ui.test.core.component.UiTestComponent},
 * {@link com.github.ui.test.core.predicate.UiTestComponentPredicate}, ... use this
 * implementation
 */
public abstract class UiTestEnvironment implements AutoCloseable {

    private static UiTestEnvironment instance;

    /**
     * Getter for the registered environment.
     *
     * @return the UI test environment
     * @throws IllegalStateException if no environment has been registered.
     */
    public static UiTestEnvironment getEnvironment() {
        if (instance == null) {
            throw new IllegalStateException("UiTestEnvironment has not been registered.");
        }

        return instance;
    }

    /**
     * Ensures that the current singleton instance of the environment
     * is of a given type.
     * If no environment is set, then the supplier is used to create a
     * new instance.
     *
     * @param environmentType     the environment type
     * @param environmentSupplier the environment supplier
     * @param <T>                 the type of the environment
     */
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

    /**
     * Getter for the component factory
     *
     * @return the component factory
     */
    public abstract UiTestComponentFactory getComponentFactory();

    /**
     * Getter for the predicate factory
     *
     * @return the predicate factory
     */
    public abstract UiTestComponentPredicateFactory getPredicateFactory();

    /**
     * Getter for the selector factory
     *
     * @return the selector factory
     */
    public abstract SelectorFactory getSelectorFactory();

    /**
     * Getter for the assert factory
     *
     * @return the assert factory
     */
    public abstract UiTestAssertFactory getAssertFactory();

}
