package org.apache.base.dao.table;

public class Insert {
	private Object[] domains;

	public static Insert newInsert(Object[] domains) {
		Insert insert = new Insert();
		insert.setDomains(domains);
		return insert;
	}

	public Object[] getDomains() {
		return domains;
	}

	public void setDomains(Object[] domains) {
		this.domains = domains;
	}

}
