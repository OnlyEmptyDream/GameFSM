package com.kong.read.config.converter;

import com.sh.common.config.IConverter;
import com.sh.commons.util.Cast;

import java.util.HashMap;

/**
 * inaKa
 *
 * 此类为prefixconfig专用，如需同样格式的解析，把下面的 intergers 删掉，然后重新创建一个java,OK
 * 请勿删除，请勿修改
 * 配置 1#123#456&2#456#789
 * 返回 map（1，map（123，456））
 * @title
 * @date 2019/6/13/9:39
 */
public class ShuangJinHaoAndMapConverter implements IConverter {

    @Override
    public HashMap<Integer, HashMap<Integer, Integer>> convert(Object source) {
        String str = (String) source;
        if (str == null || str.trim().isEmpty()) {
            return null;
        }

        HashMap<Integer, HashMap<Integer, Integer>> waiMap = new HashMap<>(10);
        if (str != null && !"".equals(str)) {
            String[] arr = str.split("&");
            if (arr.length > 0) {
                for (String v : arr) {
                    HashMap<Integer, Integer> neiMap = new HashMap<>(10);
                    String[] temp = v.split("#");
                    if (temp.length == 3) {
                        int itemId = Cast.toInteger(temp[0]);
                        int itemCount = Cast.toInteger(temp[1]);
                        int content = Cast.toInteger(temp[2]);
                        neiMap.put(itemCount, content);
                        waiMap.put(itemId,neiMap);

                    }
                }
            }
        }
        return waiMap;
    }
}
