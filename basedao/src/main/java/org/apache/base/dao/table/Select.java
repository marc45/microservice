package org.apache.base.dao.table;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class Select extends Where {
	private Class<?> clz;

	private String[] properties;

	private int start;

	private int pageSize;

	public String[] getProperties() {
		return this.properties;
	}

	public void setProperties(String[] properties) {
		this.properties = properties;
	}

	public Class<?> getClz() {
		return this.clz;
	}

	public void setClz(Class<?> clz) {
		this.clz = clz;
	}

	public static Select newSelect(Class<?> clz) {
		Select select = new Select();
		select.setClz(clz);
		select.setProperties(toProperties(clz));
		return select;
	}

	public Select limit(int start, int pageSize) {
		this.start = start;
		this.pageSize = pageSize;
		return this;
	}

	public static Select newSelect(Class<?> clz, String[] properties) {
		Select select = new Select();
		select.setClz(clz);
		select.setProperties(properties);
		return select;
	}

	private static String[] toProperties(Class<?> clz) {
		Field[] fields = clz.getDeclaredFields();

		List<String> propertyList = new ArrayList<>(fields.length);
		for (Field field : fields) {
			String columnName = field.getName();
			propertyList.add(columnName);
		}

		return (String[]) propertyList.toArray(new String[propertyList.size()]);
	}

	public int getStart() {
		return start;
	}

	public int getPageSize() {
		return pageSize;
	}

}
