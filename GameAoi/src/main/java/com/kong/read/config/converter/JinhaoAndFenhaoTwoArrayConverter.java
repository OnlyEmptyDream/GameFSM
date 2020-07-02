package com.kong.read.config.converter;

import com.sh.common.config.IConverter;

/** 
 * #和;隔开组成的二维数组
 * @author zhangli
 * 2017年6月2日 上午5:57:58   
 */
public class JinhaoAndFenhaoTwoArrayConverter implements IConverter {

	@Override
	public Object convert(Object source) {
		
		String str = (String) source;
		if(str == null || str.trim().isEmpty()) {
			return new int[0][];
		}
		
		String[] outerArray = str.split(";");
		int[][] ret = new int[outerArray.length][]; 
		for(int i = 0; i < outerArray.length; i++) {
			
			String outer = outerArray[i];
			String[] innerArray = outer.split("#");
			int[] inner = new int[innerArray.length];
			for(int j = 0; j < innerArray.length; j++) {
				inner[j] = Integer.parseInt(innerArray[j]);
			}
			
			ret[i] = inner;
		}
		return ret;
	}

}
