package com.kong.read.config.converter;

import com.sh.common.config.IConverter;
import com.sh.commons.util.Cast;
import com.sh.commons.util.Symbol;

import java.util.TreeMap;

/**
 * @author Peanut
 * @date 2019/5/20 17:11
 */
public class ShuxianAndYuhaoMapConvert implements IConverter {
    @Override
    public Object convert(Object source) {
        String str = (String) source;
        TreeMap<Integer, Integer> map = new TreeMap<>();
        if (str != null && !"".equals(str)) {
            String[] arr = str.split(Symbol.AND);
            if (arr.length > 0) {
                for (String v : arr) {
                    String[] temp = v.split(Symbol.SHUXIAN);
                    if (temp.length == 2) {
                        int key = Cast.toInteger(temp[0]);
                        int value = Cast.toInteger(temp[1]);
                        map.put(key, value);
                    }
                }
            }
        }
        return map;
    }
}
