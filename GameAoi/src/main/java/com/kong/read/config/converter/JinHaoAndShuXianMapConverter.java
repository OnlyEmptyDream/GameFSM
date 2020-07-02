package com.kong.read.config.converter;

import com.sh.common.config.IConverter;
import com.sh.commons.util.Cast;
import com.sh.commons.util.StringUtil;
import com.sh.commons.util.Symbol;

import java.util.TreeMap;

public class JinHaoAndShuXianMapConverter implements IConverter {
    @Override
    public Object convert(Object source) {
        String str = (String) source;
        TreeMap<Integer, Integer> map = new TreeMap<>();
        if (!StringUtil.isEmpty(str)) {
            String[] arr = str.split(Symbol.SHUXIAN);
            if (arr.length > 0) {
                for (String v : arr) {
                    String[] temp = v.split(Symbol.JINHAO);
                    if (temp.length == 2) {
                        map.put(Cast.toInteger(temp[0]),
                                Cast.toInteger(temp[1]));
                    }
                }
            }
        }
        return map;
    }
}
