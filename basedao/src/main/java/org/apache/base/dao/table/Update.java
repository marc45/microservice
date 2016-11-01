package org.apache.base.dao.table;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Id;

import org.apache.base.util.ReflectUtils;

public class Update extends Where {
	private Object update;
	private String[] properties;
	
	private static Update newUpdate(Object obj) {
		Update update = new Update();
		return update;
	}


	public static Update newUpdate(Object obj, String[] properties) {
		Update update = newUpdate(obj).addSetProperties(properties) ;
		return update;
	}

	public static Update newUpdate(Object obj, boolean onlyNotNull) {
		Update update = newUpdate(obj).toProperties(onlyNotNull);
		return update;
	}
	
	public static Update newUpdateById(Object obj) {
		Object[] result = ReflectUtils.invokeGetId(obj, obj.getClass()) ;
		Update update = (Update) newUpdate(obj).where().equals(result[0].toString(),result[1]) ;
		return update;
	}
	
	

	public String[] getProperties() {
		return this.properties;
	}

	public Update addSetProperties(String[] properties) {
		this.properties = properties;
		return this;
	}

	private Update toProperties(boolean isNotNull) {
		Class<?> clz = this.update.getClass();
		Field[] fields = clz.getDeclaredFields();

		List<String> propertyList = null;
		if (isNotNull) {
			propertyList = new ArrayList<>();
		} else {
			propertyList = new ArrayList<>(fields.length);
		}

		for (Field field : fields) {
			Id id = (Id) field.getAnnotation(Id.class);
			if (id == null) {
				if (!isNotNull) {
					propertyList.add(field.getName());
				} else {
					Object value = ReflectUtils.invokeGet(this.update, clz, field.getName());
					if (value != null) {
						propertyList.add(field.getName());
					}
				}
			}
		}

		this.properties = ((String[]) propertyList.toArray(new String[propertyList.size()]));
		return this ;
	}

	public Object getUpdate() {
		return this.update;
	}

	public void setUpdate(Object update) {
		this.update = update;
	}
}
