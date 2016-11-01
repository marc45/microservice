package org.apache.base.dao.table;

public enum ConditionType {
	LT("<"), GT(">"), LIKE("LIKE"), NOT_EQUAL("<>"), LT_EQUAL("<="), GT_EQUAL(">="), EQUAL("="), IN("in");

	private String condition;

	private ConditionType(String condition) {
		this.condition = condition;
	}

	public String getCondition() {
		return this.condition;
	}
}
