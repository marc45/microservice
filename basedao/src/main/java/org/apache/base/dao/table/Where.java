package org.apache.base.dao.table;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class Where {
	private Condition condition;
	private Condition currentCon;
	protected Map<String, Object> data = new HashMap<>();

	public Where where() {
		return this;
	}

	public Where and() {
		op(LogicType.AND);
		return this;
	}

	public Where or() {
		op(LogicType.OR);
		return this;
	}

	private void op(LogicType logicType) {
		this.currentCon.setLogicType(logicType);
		Condition newCon = new Condition();
		this.currentCon.setNext(newCon);
		this.currentCon = newCon;
	}

	private void addCondition(String key, Object value, String property, ConditionType type) {
		if (this.condition == null) {
			this.condition = new Condition(property, type, property);
		} else {
			this.currentCon.setKey(key);
			this.currentCon.setProperty(property);
			this.currentCon.setType(type);
		}
		this.data.put(key, value);
		this.currentCon = this.condition;
	}

	public Where equals(String property, Object value) {
		addCondition(property, value, property, ConditionType.EQUAL);
		return this;
	}

	public Where ltEquals(String key, String property, Object value) {
		addCondition(key, value, property, ConditionType.LT_EQUAL);
		return this;
	}

	public Where ltEquals(String property, Object value) {
		addCondition(property, value, property, ConditionType.LT_EQUAL);
		return this;
	}

	public Where gtEquals(String key, String property, Object value) {
		addCondition(key, value, property, ConditionType.GT_EQUAL);
		return this;
	}

	public Where gtEquals(String property, Object value) {
		addCondition(property, value, property, ConditionType.GT_EQUAL);
		return this;
	}

	public Where in(String property, List<?> objs) {
		addCondition(property, objs, property, ConditionType.IN);
		return this;
	}

	public Condition getCondition() {
		return this.condition;
	}

	public Map<String, Object> getData() {
		return this.data;
	}
}
