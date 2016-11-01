package org.apache.base.dao.provider;

import java.lang.reflect.Field;

import javax.persistence.Column;
import javax.persistence.Table;

import org.apache.base.dao.table.Condition;
import org.apache.base.dao.table.ConditionType;
import org.apache.base.dao.table.Where;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.base.Preconditions;

public abstract class SqlProvider {
	private static final Logger LOGGER = LoggerFactory.getLogger(SqlProvider.class);

	protected String getTableName(Class<?> clz) {
		Table table = (Table) clz.getAnnotation(Table.class);
		Preconditions.checkNotNull(table);

		String tableName = table.name();
		Preconditions.checkNotNull(tableName);
		return tableName;
	}

	protected String getColumnName(Field field) {
		String columnName = field.getName();
		Column column = (Column) field.getAnnotation(Column.class);
		if (column != null) {
			columnName = column.name();
			Preconditions.checkNotNull(columnName);
		}

		return columnName;
	}

	protected String getWhereClause(Where where, Class<?> clz) {
		String whereSql = "";
		if (where.getCondition() != null) {
			StringBuilder whereBuilder = new StringBuilder("WHERE");
			whereBuilder.append(" ");
			Condition condition = where.getCondition();
			try {
				while (condition != null) {

					whereBuilder.append(getColumnName(clz.getDeclaredField(condition.getProperty())))
							.append(condition.getType().getCondition()).append("#{").append("data.")
							.append(condition.getKey()).append("}");

					if (condition.getLogicType() != null) {
						whereBuilder.append(" ").append(condition.getLogicType().name()).append(" ");
					}
					condition = condition.getNext();
				}
			} catch (NoSuchFieldException | SecurityException e) {
				LOGGER.error(e.getMessage(), e);
				throw new RuntimeException(e.getMessage());
			}

			whereSql = whereBuilder.toString();
		}

		return whereSql;
	}

	public static void main(String[] args) {
		ConditionType cond = ConditionType.EQUAL;
		System.out.println(cond.getCondition());
	}
}