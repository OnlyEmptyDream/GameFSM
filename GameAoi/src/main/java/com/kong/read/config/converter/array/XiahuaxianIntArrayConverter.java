package com.kong.read.config.converter.array;

import com.sh.common.config.IConverter;

/**
 * @author qq
 */
public class XiahuaxianIntArrayConverter implements IConverter {

    @Override
    public Object convert(Object source) {
        String str = (String) source;
        if(str == null || str.trim().isEmpty()) {
            return new int[0];
        }

        String[] strArray = str.split("_");
        int[] ret = new int[strArray.length];
        for(int i = 0; i < strArray.length; i++) {
            ret[i] = Integer.parseInt(strArray[i]);
        }
        return ret;
    }

}
