package org.apache.base.dao.provider;

import java.lang.reflect.Field;

import org.apache.base.dao.table.Insert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class InsertSqlProvider extends SqlProvider {
	private static final Logger LOGGER = LoggerFactory.getLogger(InsertSqlProvider.class);

	public <T> String insert(Insert insert) {
		Object obj = insert.getInsert();
		StringBuilder insertBuilder = new StringBuilder();

		Class<?> clz = obj.getClass();
		String tableName = getTableName(clz);
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("table => {}", tableName);
		}

		StringBuilder propertiesBuilder = new StringBuilder();
		StringBuilder valuesBuilder = new StringBuilder();

		Field[] fields = clz.getDeclaredFields();
		for (Field field : fields) {
			String columnName = getColumnName(field);
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("column => {}", columnName);
			}

			propertiesBuilder.append(columnName).append(",");
			valuesBuilder.append("#{").append("insert.").append(field.getName()).append("}").append(",");
		}

		propertiesBuilder.deleteCharAt(propertiesBuilder.length() - 1);
		valuesBuilder.deleteCharAt(valuesBuilder.length() - 1);

		insertBuilder.append("INSERT INTO").append(" ").append(tableName).append(" ").append("(")
				.append(propertiesBuilder.toString()).append(")").append(" ").append("values").append("(")
				.append(valuesBuilder.toString()).append(")");
		String insertSql = insertBuilder.toString();
		LOGGER.info("insert sql => {}", insertSql);
		return insertSql;
	}
}