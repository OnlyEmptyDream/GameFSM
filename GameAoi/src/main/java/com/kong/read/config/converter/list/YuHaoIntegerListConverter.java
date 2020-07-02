package com.kong.read.config.converter.list;

import com.sh.common.config.IConverter;
import com.sh.commons.util.Cast;
import com.sh.commons.util.Symbol;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author 汪柄锐 <wangbingruia@qq.com>
 * @version 创建时间：2018年03月24日 16:16
 */
public class YuHaoIntegerListConverter implements IConverter {

    @Override
    public List<Integer> convert(Object source) {
        String v = (String) source;
        if (v == null || StringUtils.isEmpty(v)) {
            return Collections.emptyList();
        }

        List<Integer> ret = new ArrayList<>();
        String[] strArray = v.split(Symbol.AND);
        for (String str : strArray) {
            ret.add(Cast.toInteger(str));
        }
        return ret;
    }
}
