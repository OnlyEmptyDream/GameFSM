package com.kong.read.config.converter.array;


import com.sh.common.config.IConverter;
import com.sh.commons.util.Cast;
import org.apache.commons.lang3.StringUtils;

/**
 * 转换成二维数组
 * 
 * @author 杨 强
 *
 */
public class JinHaoAndFenHaoArrayConverter implements IConverter {

    @Override
    public int[][] convert(Object source) {
        String strVlaue = (String) source;
        if (StringUtils.isEmpty(strVlaue)) {
            return new int[0][0];
        } else {
            String[] allArray = strVlaue.split(";");
            int[][] request = new int[allArray.length][];
            for (int i = 0; i < allArray.length; i++) {
                String[] itemArray = allArray[i].split("#");
                int[] req = new int[itemArray.length];
                for (int j = 0; j < itemArray.length; j++) {
                    req[j] = Cast.toInteger(itemArray[j]);
                }
                request[i] = req;
            }
            return request;
        }

    }

}
