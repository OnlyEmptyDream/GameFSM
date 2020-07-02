package com.kong.read.config.converter.list;

import com.sh.common.config.IConverter;
import com.sh.commons.util.Cast;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author qq
 */
public class XiahuaxianIntegerListConverter implements IConverter {

    @Override
    public List<Integer> convert(Object source) {
        String v = (String) source;
        if (v == null || StringUtils.isEmpty(v)) {
            return Collections.emptyList();
        }

        List<Integer> ret = new ArrayList<>();
        String[] strArray = v.split("_");
        for (String str : strArray) {
            ret.add(Cast.toInteger(str));
        }
        return ret;
    }
}
