package org.apache.base.dao.provider;

import com.google.common.base.Preconditions;
import java.lang.reflect.Field;

import org.apache.base.dao.table.Select;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SelectSqlProvider extends SqlProvider {
	private static Logger LOGGER = LoggerFactory.getLogger(SelectSqlProvider.class);

	public String select(Select select) {
		StringBuilder selectBuilder = new StringBuilder();
		selectBuilder.append("SELECT").append(" ");

		Class<?> clz = select.getClz();
		Field field = null;
		String[] properties = select.getProperties();
		try {
			for (String property : properties) {
				field = clz.getDeclaredField(property);

				Preconditions.checkNotNull(field);
				selectBuilder.append(getColumnName(field)).append(" ").append("AS").append(" ").append(property)
						.append(",");
			}
		} catch (NoSuchFieldException | SecurityException e) {
			LOGGER.error(e.getMessage(), e);
			throw new RuntimeException(e.getMessage());
		}
		selectBuilder.deleteCharAt(selectBuilder.length() - 1);
		selectBuilder.append(" ").append("FROM").append(" ").append(getTableName(clz)).append(" ")
				.append(getWhereClause(select, clz));

		String selectSql = selectBuilder.toString();

		return selectSql;
	}
}
