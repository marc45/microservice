package org.apache.base.dao.provider;

import java.lang.reflect.Field;

import org.apache.base.dao.table.Select;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.base.Preconditions;

public class SelectCountProvider extends SqlProvider {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(SelectCountProvider.class) ;
	
	public String selectCount(Select select) {
		StringBuilder selectCountBuilder = new StringBuilder();
		selectCountBuilder.append("SELECT").append(" ");

		Class<?> clz = select.getClz();
		Field field = null;
		String[] properties = select.getProperties();
		Preconditions.checkState(properties.length == 1, "properties length expected %s but was %s",1,properties.length);
		try {
			for (String property : properties) {
				field = clz.getDeclaredField(property);

				Preconditions.checkNotNull(field);
				selectCountBuilder.append("count").append("(").append(getColumnName(field)).append("").append(" ");
			}
		} catch (NoSuchFieldException | SecurityException e) {
			throw new RuntimeException(e);
		}
		selectCountBuilder.deleteCharAt(selectCountBuilder.length() - 1);
		selectCountBuilder.append(" ").append("FROM").append(" ").append(getTableName(clz)).append(" ")
				.append(getWhereClause(select, clz));

		String selectCountSql = selectCountBuilder.toString();
		
		if(LOGGER.isDebugEnabled()){
			LOGGER.debug("select count sql {}",selectCountSql);
		}

		return selectCountSql;
	}

}
