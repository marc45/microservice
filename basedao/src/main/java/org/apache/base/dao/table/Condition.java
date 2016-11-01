package org.apache.base.dao.table;

public class Condition {
	private String property;

	private String key;
	private ConditionType type = ConditionType.EQUAL;

	private LogicType logicType;

	private Condition prev;

	private Condition next;

	public Condition() {
	}

	public Condition(String property) {
		this.property = property;
	}

	public Condition getPrev() {
		return this.prev;
	}

	public void setPrev(Condition prev) {
		this.prev = prev;
	}

	public Condition(String property, ConditionType type, String key) {
		this.property = property;
		this.type = type;
		this.key = key;
	}

	public Condition(String property, ConditionType type, LogicType logicType) {
		this.property = property;
		this.type = type;
		this.logicType = logicType;
	}

	public String getKey() {
		return this.key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getProperty() {
		return this.property;
	}

	public ConditionType getType() {
		return this.type;
	}

	public LogicType getLogicType() {
		return this.logicType;
	}

	public void setLogicType(LogicType logicType) {
		this.logicType = logicType;
	}

	public Condition getNext() {
		return this.next;
	}

	public void setNext(Condition next) {
		this.next = next;
	}

	public void setProperty(String property) {
		this.property = property;
	}

	public void setType(ConditionType type) {
		this.type = type;
	}
}
