package org.apache.base.dao.provider;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import org.apache.base.dao.table.Insert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class InsertSqlProvider extends SqlProvider {
	private static final Logger LOGGER = LoggerFactory.getLogger(InsertSqlProvider.class);

	public <T> String insert(Insert insert) {
		//性能优化点，StringBuilder 扩容问题
		Object[] domains = insert.getDomains();
		StringBuilder insertBuilder = new StringBuilder();

		Class<?> clz = domains[0].getClass();
		String tableName = getTableName(clz);
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("table => {}", tableName);
		}

		StringBuilder propertiesBuilder = new StringBuilder();
		StringBuilder valuesBuilder = new StringBuilder();

		List<String> columnNames = new ArrayList<>() ;
		Field[] fields = clz.getDeclaredFields();
		for (Field field : fields) {
			String columnName = getColumnName(field);
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("column => {}", columnName);
			}
			propertiesBuilder.append(columnName).append(",");
			columnNames.add(columnName) ;
		}
		propertiesBuilder.deleteCharAt(propertiesBuilder.length() - 1);
		
		for(int index = 0 ,len = domains.length ;index < len ;index++){
			valuesBuilder.append("(");
			for(String columnName : columnNames){
				valuesBuilder.append("#{").append("domains["+index+"].").append(columnName).append("}").append(",");
			}
			valuesBuilder.deleteCharAt(valuesBuilder.length() - 1).append(")").append(",");
		}
		valuesBuilder.deleteCharAt(valuesBuilder.length() - 1) ;

		insertBuilder.append("INSERT INTO").append(" ").append(tableName).append(" ").append("(")
				.append(propertiesBuilder.toString()).append(")").append(" ").append("values")
				.append(valuesBuilder.toString());
		String insertSql = insertBuilder.toString();
		LOGGER.info("insert sql => {}", insertSql);
		return insertSql;
	}
}