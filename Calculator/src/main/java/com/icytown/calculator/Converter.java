package com.icytown.calculator;

/**
 * 转换器类, 提供各种类型转换的静态方法
 */
public final class Converter {
    private Converter() {
        // 用于隐藏Converter, 使其不能实例化
    }

    /**
     * Double -> String
     * 如果值为整数, 生成的字符串中不会带有小数点
     */
    public static String convertDoubleToString(double val) {
        if (Math.round(val) - val == 0) {
            return String.valueOf((int)val);
        } else {
            return String.valueOf(val);
        }
    }
}
