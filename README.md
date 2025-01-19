# UI Test Components

This library provides basic building blocks for UI-Tests / end-to-end tests using Java.
The core concept is based on the Page-Object design pattern for those tests.
However, in modern web apps, it is more common to reuse single components.
Thus, the idea is to have the same structure for the UI-Tests, i.e., each component (in e.g. a React app) 
corresponds to a UITestComponent in the tests.
In addition, the library is built such that the usage does not depend on the underlying framework (Selenium, Playwright, ...).
At one point, the developer has to initialize a UiTestEnvironment (currently only Playwright is supported)
and then can use the components and predicates (for filter) independently of the environment.

This library is developed with the following picture in mind:
We have Java backend (spring-boot, ...) and some web frontend (React, Angular, ...).
Since the backend might be tested with integration tests and unit tests in Java,
a project might already contain many utilities for generating test data in Java.
The main reason for having UI-Tests in Java and not JavaScript is that these generators should
also be usable for these tests.
This should reduce the overload for creating test data generators.

## Core

The core provides the structure classes and interface for this library without any dependency of the underlying
framework.

## Playwright

The Playwright artifact contains an implementation of the Playwright framework for UI-Tests

## Examples

Example setups are provided under `examples`.
These show the basic usage of the library.