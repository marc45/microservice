package org.apache.micro.cache.domain;

import javax.persistence.Id;

public class TableDomain {

	@Id
	private long id;

	// 相关联数据源ID
	private long dataSourceId;

	private String tableName;

	private String keyField;

	// 以逗号隔开
	private String fields;

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public String getFields() {
		return fields;
	}

	public void setFields(String fields) {
		this.fields = fields;
	}

	public String getKeyField() {
		return keyField;
	}

	public void setKeyField(String keyField) {
		this.keyField = keyField;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getDataSourceId() {
		return dataSourceId;
	}

	public void setDataSourceId(long dataSourceId) {
		this.dataSourceId = dataSourceId;
	}

}
