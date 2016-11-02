package org.apache.micro.cache.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.CountDownLatch;

import org.apache.micro.cache.domain.DataSourceDomain;
import org.apache.micro.cache.domain.StringObject;
import org.apache.micro.cache.domain.TableDomain;

public class DataSynModel {

	private static DataSynModel dataSyn = new DataSynModel();

	private static Map<Long, DataSourceDomain> dsMap = new HashMap<>();

	private static Queue<StringObject> sobjectQueue = new ConcurrentLinkedQueue<>();

	private static Queue<TableDomain> tableQueue = new ConcurrentLinkedQueue<>();
	/**
	 * 0 进行中 1 已完成
	 */
	private CountDownLatch platch = null;

	// private CountDownLatch clatch = null;

	private DataSynModel() {

	}

	public static DataSynModel getInstance() {
		return dataSyn;
	}

	public StringObject pollStringObject() {
		return sobjectQueue.poll();
	}

	public void addStringObject(StringObject sobject) {
		sobjectQueue.add(sobject);
	}

	public TableDomain pollTableDomain() {
		return tableQueue.poll();
	}

	public void addTableDomains(List<TableDomain> domains) {
		for (TableDomain domain : domains) {
			tableQueue.add(domain);
		}
	}

	public CountDownLatch getPlatch() {
		return platch;
	}

	public void setPlatch(CountDownLatch platch) {
		this.platch = platch;
	}

	public void put(Long key, DataSourceDomain value) {
		dsMap.put(key, value);
	}

	public void put(Long key) {
		dsMap.get(key);
	}

	public Map<Long, DataSourceDomain> getDsMap() {
		return dsMap;
	}

}
