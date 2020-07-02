package com.kong.read.config.converter.list;

import com.sh.common.config.IConverter;
import com.sh.commons.util.Cast;
import com.sh.commons.util.Symbol;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Peanut
 * @date 2019/5/16 14:53
 */
public class JinHaoAndYuhaoAndShuxianListConverter implements IConverter {
    @Override
    public List<Map<Integer,Integer>> convert(Object source) {
        String strVlaue = (String) source;
        List<Map<Integer,Integer>> list = new ArrayList<Map<Integer,Integer>>();
        if (StringUtils.isEmpty(strVlaue)) {
            return new ArrayList<>();
        } else {
            String[] allArray = strVlaue.split(Symbol.SHUXIAN);
            for (String s : allArray) {
                Map<Integer,Integer> map = new HashMap<>();
                String[] subArray = s.split(Symbol.AND);
                for (String sub: subArray)
                {
                    String[] iSub = sub.split(Symbol.JINHAO);
                    if (iSub.length == 2)
                    {
                        map.put(Cast.toInteger(iSub[0]), Cast.toInteger(iSub[1]));
                    }
                }

                list.add(map);
            }
        }
        return list;
    }
}
