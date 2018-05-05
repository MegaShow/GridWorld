package com.icytown.imagereader;

import imagereader.Runner;

/**
 * ImageReaderRunner provides the static main method in project.
 */
public final class ImageReaderRunner {
    private ImageReaderRunner() {
        // Do nothing
    }

    public static void main(String[] args) {
        ImplementImageIO imageioer = new ImplementImageIO();
        ImplementImageProcessor processor = new ImplementImageProcessor();
        Runner.run(imageioer, processor);
    }
}
