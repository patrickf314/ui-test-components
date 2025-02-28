package com.github.ui.test.common.io;

import java.io.*;
import java.nio.charset.StandardCharsets;

/**
 * A utility class for handling IO streams
 */
public final class IOUtils {

    private IOUtils() {
        // No instances
    }

    public static String toString(InputStream inputStream) throws IOException {
        return toString(new InputStreamReader(inputStream, StandardCharsets.UTF_8));
    }

    public static String toString(Reader reader) throws IOException {
        try (var bufferedReader = new BufferedReader(reader)) {
            var builder = new StringBuilder();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                builder.append(line).append("\n");
            }
            return builder.toString();
        }
    }
}
