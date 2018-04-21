package com.icytown.calculator;

import static org.junit.Assert.*;
import org.junit.Test;

import java.lang.reflect.*;

/**
 * 测试类, Converter
 */
public class ConverterTest {
    /**
     * 测试方法(String)Converter.convertDoubleToString(double)
     */
    @Test(timeout=5000)
    public void converter() {
        assertEquals("0", Converter.convertDoubleToString(0));
        assertEquals("1", Converter.convertDoubleToString(1));
        assertEquals("2", Converter.convertDoubleToString(2));
        assertEquals("0.125", Converter.convertDoubleToString(0.125));
        assertEquals("123457890", Converter.convertDoubleToString(123457890));
        assertEquals("120.23", Converter.convertDoubleToString(120.23));
    }
}
