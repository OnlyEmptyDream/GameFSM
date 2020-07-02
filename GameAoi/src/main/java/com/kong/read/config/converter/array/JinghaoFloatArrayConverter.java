package com.kong.read.config.converter.array;

import com.sh.common.config.IConverter;

/**
 * Author :Yejingjing
 * Date   :2018-03-22 16:28.
 * Desc:
 */
public class JinghaoFloatArrayConverter implements IConverter {
    @Override
    public Object convert(Object source) {
        String str = (String) source;
        if (str == null || str.trim().isEmpty()) {
            return new int[0];
        }

        String[] strArray = str.split("#");
        float[] ret = new float[strArray.length];
        for (int i = 0; i < strArray.length; i++) {
            ret[i] = Integer.parseInt(strArray[i]);
        }
        return ret;
    }
}
