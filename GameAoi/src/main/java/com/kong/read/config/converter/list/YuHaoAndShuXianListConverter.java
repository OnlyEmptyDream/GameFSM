package com.kong.read.config.converter.list;

import com.sh.common.config.IConverter;
import com.sh.commons.util.StringUtil;
import com.sh.commons.util.Symbol;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author :汪柄锐 <wangbingruia@qq.com>
 * @date :2017/8/5 10:05
 */
public class YuHaoAndShuXianListConverter implements IConverter {

    @Override
    public List<int[]> convert(Object source) {
        String strVlaue = (String) source;
        List<int[]> list = new ArrayList<>();
        if (!StringUtils.isEmpty(strVlaue)) {
            String[] allArray = strVlaue.split(Symbol.SHUXIAN);
            for (String s : allArray) {
                String[] typeArray = s.split(Symbol.AND);
                list.add(StringUtil.strArrToIntArr(typeArray));
            }
        }
        return list;
    }
}
