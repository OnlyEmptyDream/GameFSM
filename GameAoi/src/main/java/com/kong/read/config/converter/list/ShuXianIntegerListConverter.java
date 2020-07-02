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
* @version 创建时间：2017年7月10日 下午2:10:53
* 
*/
public class ShuXianIntegerListConverter implements IConverter {

    @Override
    public List<Integer> convert(Object source) {
        String v = (String) source;
        if (v == null || StringUtils.isEmpty(v)) {
            return Collections.emptyList();
        }

        List<Integer> ret = new ArrayList<>();
        String[] strArray = v.split(Symbol.SHUXIAN);
        for (String str : strArray) {
            ret.add(Cast.toInteger(str));
        }
        return ret;
    }

}
