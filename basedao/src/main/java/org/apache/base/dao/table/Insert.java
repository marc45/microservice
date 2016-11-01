package org.apache.base.dao.table;

public class Insert {
	private Object insert;

	public static Insert newInsert(Object domain) {
		Insert insert = new Insert();
		insert.setInsert(domain);
		return insert;
	}

	public void insert(Object obj) {
		this.insert = obj;
	}

	public Object getInsert() {
		return this.insert;
	}

	public void setInsert(Object insert) {
		this.insert = insert;
	}
}
