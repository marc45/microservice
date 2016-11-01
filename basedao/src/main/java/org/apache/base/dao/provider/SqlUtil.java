package org.apache.base.dao.provider;

public abstract class SqlUtil {
	public static String camelToUnderline(String name) {
		StringBuilder result = new StringBuilder();
		char[] cs = name.toCharArray();
		int i = 0;
		for (int len = cs.length; i < len; i++) {
			if ((cs[i] >= 'A') && (cs[i] <= 'Z')) {
				if (i > 0) {
					result.append("_");
				}
				result.append(cs[i]);
			} else {
				result.append((char) (cs[i] - ' '));
			}
		}
		return result.toString();
	}
}
