package com.kong.read.config.converter.list;


import com.sh.common.config.IConverter;
import com.sh.commons.util.StringUtil;
import com.sh.commons.util.Symbol;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 转换成数组List
 *
 * @author xiaomo
 */
public class JinHaoAndShuXianListConverter implements IConverter {

    @Override
    public List<int[]> convert(Object source) {
        String strVlaue = (String) source;
        List<int[]> list = new ArrayList<>();
        if (StringUtils.isEmpty(strVlaue)) {
            return new ArrayList<>();
        } else {
            String[] allArray = strVlaue.split(Symbol.SHUXIAN);
            for (String s : allArray) {
                String[] typeArray = s.split(Symbol.JINHAO);
                list.add(StringUtil.strArrToIntArr(typeArray));
            }
        }
        return list;
    }
}