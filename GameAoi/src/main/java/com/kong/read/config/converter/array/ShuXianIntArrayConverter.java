package com.kong.read.config.converter.array;

import com.sh.common.config.IConverter;
import com.sh.commons.util.Symbol;

/**
 * @author 汪柄锐 <wangbingruia@qq.com>
 * @version 创建时间：2018年05月15日 9:31
 */
public class ShuXianIntArrayConverter implements IConverter {
    @Override
    public Object convert(Object source) {
        String str = (String) source;
        if (str == null || str.trim().isEmpty()) {
            return new int[0];
        }

        String[] strArray = str.split(Symbol.SHUXIAN);
        int[] ret = new int[strArray.length];
        for (int i = 0; i < strArray.length; i++) {
            ret[i] = Integer.parseInt(strArray[i]);
        }
        return ret;
    }
}
