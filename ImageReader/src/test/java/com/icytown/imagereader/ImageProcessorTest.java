package com.icytown.imagereader;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.logging.Logger;

/**
 * Test class of ImageReader project.
 */
public class ImageProcessorTest {
    private static final String PATH = "out/test/ImageReader/";
    private static final String OUTPATH = "out/";
    private static final String GOALPATH = "goal/";
    private static final String BMPFIXNAME = ".bmp";

    /**
     * Initialize before testing.
     */
    @BeforeClass
    public static void setup() {
        ImplementImageIO imageioer = new ImplementImageIO();
        ImplementImageProcessor processor = new ImplementImageProcessor();
        File folder = new File(PATH + OUTPATH);
        if (!folder.exists()) {
            folder.mkdir();
        }
        for (int i = 1; i <= 2; i++) {
            try {
                Image image = imageioer.myRead(PATH + i + BMPFIXNAME);
                imageioer.myWrite(image, PATH + OUTPATH + i + BMPFIXNAME);
                imageioer.myWrite(processor.showChanelR(image), PATH + OUTPATH + i + "_red.bmp");
                imageioer.myWrite(processor.showChanelG(image), PATH + OUTPATH + i + "_green.bmp");
                imageioer.myWrite(processor.showChanelB(image), PATH + OUTPATH + i + "_blue.bmp");
                imageioer.myWrite(processor.showGray(image), PATH + OUTPATH + i + "_gray.bmp");
            } catch (IOException e) {
                Logger logger = Logger.getLogger("ImageProcessorTest");
                logger.info(e.toString());
            }
        }
    }

    /**
     * Test method myWrite() in class ImplementImageIO.
     */
    @Test(timeout = 5000)
    public void myWrite() {
        for (int i = 1; i <= 2; i++) {
            String expected = PATH + i + BMPFIXNAME;
            String actual = PATH + OUTPATH + i + BMPFIXNAME;
            assertEqualsImage(expected, actual);
        }
    }

    /**
     * Test method showChanelR() in class ImplementImageProcessor.
     */
    @Test(timeout = 5000)
    public void showChanelR() {
        for (int i = 1; i <= 2; i++) {
            String expected = PATH + GOALPATH + i + "_red_goal.bmp";
            String actual = PATH + OUTPATH + i + "_red.bmp";
            assertEqualsImage(expected, actual);
        }
    }

    /**
     * Test method showChanelG() in class ImplementImageProcessor.
     */
    @Test(timeout = 5000)
    public void showChanelG() {
        for (int i = 1; i <= 2; i++) {
            String expected = PATH + GOALPATH + i + "_green_goal.bmp";
            String actual = PATH + OUTPATH + i + "_green.bmp";
            assertEqualsImage(expected, actual);
        }
    }

    /**
     * Test method showChanelB() in class ImplementImageProcessor.
     */
    @Test(timeout = 5000)
    public void showChanelB() {
        for (int i = 1; i <= 2; i++) {
            String expected = PATH + GOALPATH + i + "_blue_goal.bmp";
            String actual = PATH + OUTPATH + i + "_blue.bmp";
            assertEqualsImage(expected, actual);
        }
    }

    /**
     * Test method showGray() in class ImplementImageProcessor.
     */
    @Test(timeout = 5000)
    public void showGray() {
        for (int i = 1; i <= 2; i++) {
            String expected = PATH + GOALPATH + i + "_gray_goal.bmp";
            String actual = PATH + OUTPATH + i + "_gray.bmp";
            assertEqualsImage(expected, actual);
        }
    }

    /**
     * Check if two images are the same with Width, Height and all the RGB values.
     *
     * @param expected the path of expected image file
     * @param actual the path of actual image file
     */
    private void assertEqualsImage(String expected, String actual) {
        try {
            BufferedImage goalImage = ImageIO.read(new FileInputStream(expected));
            BufferedImage outImage = ImageIO.read(new FileInputStream(actual));
            int width = goalImage.getWidth(null);
            int height = goalImage.getHeight(null);
            assertEquals(width, outImage.getWidth(null));
            assertEquals(height, outImage.getHeight(null));
            for (int h = 0; h < height; h++) {
                for (int w = 0; w < width; w++) {
                    assertEquals("Position (" + w + "," + h + ")", goalImage.getRGB(w, h), outImage.getRGB(w, h));
                }
            }
        } catch (IOException e) {
            Logger logger = Logger.getLogger("ImageProcessorTest");
            logger.info(e.toString());
        }
    }
}
