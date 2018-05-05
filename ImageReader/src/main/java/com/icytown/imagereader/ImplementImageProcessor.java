package com.icytown.imagereader;

import imagereader.IImageProcessor;

import java.awt.*;
import java.awt.image.FilteredImageSource;
import java.awt.image.ImageFilter;
import java.awt.image.RGBImageFilter;

/**
 * ImplementImageProcessor provides the tools to filter an image with RGB value.
 */
public class ImplementImageProcessor implements IImageProcessor {
    private static final int TRANSPARENT = 0xff000000;
    private static final int RED = 0x00ff0000;
    private static final int GREEN = 0x0000ff00;
    private static final int BLUE = 0x000000ff;

    /**
     * Show the Chanel Red of the image
     *
     * @param image the image to be processed
     * @return the image with only red chanel
     */
    @Override
    public Image showChanelR(Image image) {
        ImageFilter colorFilter = new RedSwapFilter();
        FilteredImageSource source = new FilteredImageSource(image.getSource(), colorFilter);
        return Toolkit.getDefaultToolkit().createImage(source);
    }

    /**
     * Show the Chanel Green of the image
     *
     * @param image the image to be processed
     * @return the image with only green chanel
     */
    @Override
    public Image showChanelG(Image image) {
        ImageFilter colorFilter = new GreenSwapFilter();
        FilteredImageSource source = new FilteredImageSource(image.getSource(), colorFilter);
        return Toolkit.getDefaultToolkit().createImage(source);
    }

    /**
     * Show the Chanel Blue of the image
     *
     * @param image the image to be processed
     * @return the image with only blue chanel
     */
    @Override
    public Image showChanelB(Image image) {
        ImageFilter colorFilter = new BlueSwapFilter();
        FilteredImageSource source = new FilteredImageSource(image.getSource(), colorFilter);
        return Toolkit.getDefaultToolkit().createImage(source);
    }

    /**
     * Show gray scale image
     *
     * @param image the image to be processed
     * @return the gray scale image
     */
    @Override
    public Image showGray(Image image) {
        ImageFilter colorFilter = new GraySwapFilter();
        FilteredImageSource source = new FilteredImageSource(image.getSource(), colorFilter);
        return Toolkit.getDefaultToolkit().createImage(source);
    }

    /**
     * RedSwapFilter provides a filter with only red chanel
     */
    class RedSwapFilter extends RGBImageFilter {
        @Override
        public int filterRGB(int x, int y, int rgb) {
            return rgb & (TRANSPARENT | RED);
        }
    }

    /**
     * GreenSwapFilter provides a filter with only green chanel
     */
    class GreenSwapFilter extends RGBImageFilter {
        @Override
        public int filterRGB(int x, int y, int rgb) {
            return rgb & (TRANSPARENT | GREEN);
        }
    }

    /**
     * BlueSwapFilter provides a filter with only blue chanel
     */
    class BlueSwapFilter extends RGBImageFilter {
        @Override
        public int filterRGB(int x, int y, int rgb) {
            return rgb & (TRANSPARENT | BLUE);
        }
    }

    /**
     * GraySwapFilter provides a filter to get gray scale image
     */
    class GraySwapFilter extends RGBImageFilter {
        @Override
        public int filterRGB(int x, int y, int rgb) {
            double val = 0.299 * ((rgb & RED) >> 16);
            val += 0.587 * ((rgb & GREEN) >> 8);
            val += 0.114 * (rgb & BLUE);
            int i = (int)val & 0xff;
            return TRANSPARENT | (i << 16) | (i << 8) | i;
        }
    }
}
