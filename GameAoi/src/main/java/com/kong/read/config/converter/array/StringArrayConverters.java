package com.kong.read.config.converter.array;

import com.sh.common.config.IConverter;

/**
 * Created on 2016/8/24 20:51.
 *
 * @author 梅学望
 */
public class StringArrayConverters {

	/**
	 * 井号 "#"
	 */
	public static class Pound implements IConverter {

		@Override
		public String[] convert(Object source) {
			return parseFrom(source, "#");
		}
	}

	private static String[] parseFrom(Object source, String separator) {
		if (source == null) {
			return new String[0];
		}
		String value = source.toString();
		if (value.trim().isEmpty()) {
			return new String[0];
		}
		String[] valueArray = value.split(separator);
		return valueArray;
	}
}
