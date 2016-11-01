package org.apache.micro.cache.domain;

import java.util.Map;

public class StringObject {

	private String key;

	public Map<String, String> data;

	public StringObject() {
	}

	public StringObject(String key, Map<String, String> data) {
		super();
		this.key = key;
		this.data = data;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public Map<String, String> getData() {
		return data;
	}

	public void setData(Map<String, String> data) {
		this.data = data;
	}

}
