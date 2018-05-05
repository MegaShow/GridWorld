package com.icytown.imagereader;

import imagereader.IImageIO;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ImageProducer;
import java.awt.image.MemoryImageSource;
import java.io.*;
import java.util.logging.Logger;

/**
 * ImplementImageIO provides the tools to read image from file and write image to file.
 */
public class ImplementImageIO implements IImageIO {
    private static final int BITMAP_HEADER_SIZE = 14;
    private static final int BITMAP_INFO_SIZE = 40;

    private static final int BITMAP_HEADER_ADDRESS_OFFSET = 10;
    private static final int BITMAP_INFO_WIDTH_OFFSET = 4;
    private static final int BITMAP_INFO_HEIGHT_OFFSET = 8;
    private static final int BITMAP_INFO_SIZE_OFFSET = 20;

    private static final int TRANSPARENT = 0xff000000;

    /**
     * Read an image from the file by its path.
     *
     * @param filePath the path of image file
     * @return the image
     * @throws IOException
     */
    @Override
    public Image myRead(String filePath) throws IOException {
        byte[] bitmapHeader = new byte[BITMAP_HEADER_SIZE];
        byte[] bitmapInfo = new byte[BITMAP_INFO_SIZE];
        byte[] bitmap;
        InputStream istream = new FileInputStream(filePath);
        try {
            int readHeaderSize = istream.read(bitmapHeader);
            int readInfoSize = istream.read(bitmapInfo);
            if (readHeaderSize != BITMAP_HEADER_SIZE || readInfoSize != BITMAP_INFO_SIZE) {
                return null;
            }
            int bitmapOffset = byteArrayToInt(bitmapHeader, BITMAP_HEADER_ADDRESS_OFFSET);
            int bitmapWidth = byteArrayToInt(bitmapInfo, BITMAP_INFO_WIDTH_OFFSET);
            int bitmapHeight = byteArrayToInt(bitmapInfo, BITMAP_INFO_HEIGHT_OFFSET);
            int bitmapSize = byteArrayToInt(bitmapInfo, BITMAP_INFO_SIZE_OFFSET);
            if (bitmapOffset > BITMAP_HEADER_SIZE + BITMAP_INFO_SIZE) {
                int paletteSize = bitmapOffset - BITMAP_HEADER_SIZE - BITMAP_INFO_SIZE;
                istream.skip(paletteSize);
            }
            bitmap = new byte[bitmapSize];
            istream.read(bitmap);
            int[] memoryBitmap = new int[bitmapHeight * bitmapWidth];
            int blank = bitmapSize / bitmapHeight - bitmapWidth * 3;
            for (int h = 0; h < bitmapHeight; h++) {
                int index = (bitmapHeight - h - 1) * (bitmapWidth * 3 + blank);
                for (int w = 0; w < bitmapWidth; w++, index += 3) {
                    memoryBitmap[h * bitmapWidth + w] = byteRGBToInt(bitmap[index + 2], bitmap[index + 1], bitmap[index]);
                }
            }
            ImageProducer ip = new MemoryImageSource(bitmapWidth, bitmapHeight, memoryBitmap, 0, bitmapWidth);
            return Toolkit.getDefaultToolkit().createImage(ip);
        } catch (Exception e) {
            Logger logger = Logger.getLogger("ImageReader");
            logger.info(e.toString());
        } finally {
            istream.close();
        }
        return null;
    }

    /**
     * Write an image to the file.
     *
     * @param image the image need to write
     * @param path the path of file
     * @return the image
     * @throws IOException
     */
    @Override
    public Image myWrite(Image image, String path) throws IOException {
        BufferedImage bitmap = new BufferedImage(image.getWidth(null), image.getHeight(null), BufferedImage.TYPE_INT_RGB);
        Graphics2D bGr = bitmap.createGraphics();
        bGr.drawImage(image, 0, 0, null);
        bGr.dispose();
        File file = new File(path);
        ImageIO.write(bitmap, "bmp", file);
        return image;
    }

    /**
     * Convert a byte array with length of 4 to int.
     *
     * @param arr the byte array
     * @param offset the offset of the byte array needed to be converted
     * @return value after converting
     */
    private int byteArrayToInt(byte[] arr, int offset) {
        return byteArrayToInt(arr, offset, 4);
    }

    /**
     * Convert a byte array to int.
     *
     * @param arr the byte array
     * @param offset the offset of the byte array needed to be converted
     * @param length the length of bytes needed to be converted
     * @return value after converting
     */
    private int byteArrayToInt(byte[] arr, int offset, int length) {
        int val = 0;
        for (int i = 0; i < length; i++) {
            val += (arr[i + offset] & 0x000000FF) << (i * 8);
        }
        return val;
    }

    /**
     * Convert the byte value of RGB to int
     *
     * @param r red value
     * @param g green value
     * @param b blue value
     * @return value after converting
     */
    private int byteRGBToInt(byte r, byte g, byte b) {
        int val = TRANSPARENT;
        val |= (r & 0xff) << 16;
        val |= (g & 0xff) << 8;
        val |= (b & 0xff);
        return val;
    }
}
