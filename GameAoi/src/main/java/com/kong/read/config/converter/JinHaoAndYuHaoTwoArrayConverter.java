package com.kong.read.config.converter;

import com.sh.common.config.IConverter;
import com.sh.commons.util.Symbol;

/**
 * @author Administrator
 */
public class JinHaoAndYuHaoTwoArrayConverter implements IConverter {

    @Override
    public Object convert(Object arg0) {

        String source = (String) arg0;
        if (source == null || source.trim().isEmpty()) {
            return new int[0][];
        }

        String[] outerArray = source.split(Symbol.AND);
        int[][] ret = new int[outerArray.length][];
        for (int i = 0; i < outerArray.length; i++) {

            String outer = outerArray[i];
            String[] innerArray = outer.split(Symbol.JINHAO);
            int[] inner = new int[innerArray.length];
            for (int j = 0; j < innerArray.length; j++) {
                inner[j] = Integer.parseInt(innerArray[j]);
            }
            ret[i] = inner;
        }

        return ret;
    }

}
