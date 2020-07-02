package com.kong.read.config.converter;

import com.sh.common.config.IConverter;
import com.sh.commons.util.Cast;

import java.util.HashMap;

/**
 * #和& 解析成 一个Map 例如 1#1&2#3->  1->1,2->3的map
 *
 * @author Administrator
 */
public class JinHaoAndYuHaoMapConverter implements IConverter {

    @Override
    public HashMap<Integer, Integer> convert(Object source) {
        String str = (String) source;
        HashMap<Integer, Integer> map = new HashMap<>(10);
        if (str != null && !"".equals(str)) {
            String[] arr = str.split("&");
            if (arr.length > 0) {
                for (String v : arr) {
                    String[] temp = v.split("#");
                    if (temp.length == 2) {
                        int itemId = Cast.toInteger(temp[0]);
                        int itemCount = Cast.toInteger(temp[1]);
                        Integer count = map.get(itemId);
                        map.put(itemId, (count == null ? 0 : count) + itemCount);
                    }
                }
            }
        }
        return map;
    }

}
