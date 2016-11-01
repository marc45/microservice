package org.apache.base.dao.provider;

import com.google.common.base.Preconditions;
import java.lang.reflect.Field;

import org.apache.base.dao.table.Update;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UpdateSqlProvider extends SqlProvider {
	private static final Logger LOGGER = LoggerFactory.getLogger(UpdateSqlProvider.class);

	public String update(Update update) {
		Object obj = update.getUpdate();
		StringBuilder updateBuilder = new StringBuilder();

		Class<?> clz = obj.getClass();
		String tableName = getTableName(clz);
		updateBuilder.append("UPDATE").append(" ").append(tableName).append(" ").append("SET").append(" ");

		String[] properties = update.getProperties();
		Preconditions.checkNotNull(properties);
		try {
			for (String property : properties) {
				Field field = clz.getDeclaredField(property);
				updateBuilder.append(getColumnName(field)).append("=").append("#{").append("update.")
						.append(field.getName()).append("}").append(",");
			}
			updateBuilder.deleteCharAt(updateBuilder.length() - 1);
		} catch (NoSuchFieldException | SecurityException e) {
			LOGGER.error(e.getMessage(), e);
			throw new RuntimeException(e.getMessage());
		}

		updateBuilder.append(" ").append(getWhereClause(update, clz));

		String updateSql = updateBuilder.toString();
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("update sql => {}", updateSql);
		}
		return updateSql;
	}
}
