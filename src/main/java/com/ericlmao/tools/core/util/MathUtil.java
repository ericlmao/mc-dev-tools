package com.ericlmao.tools.core.util;

import lombok.experimental.UtilityClass;

@UtilityClass
public class MathUtil {

    public float minMax(float value, float min, float max) {
        return Math.min(Math.max(value, min), max);
    }

}
