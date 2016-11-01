package org.apache.micro.cache.domain;

import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.CountDownLatch;

public class DataSynDomain {

	private static DataSynDomain dataSyn = new DataSynDomain();

	private static Queue<StringObject> sobjectQueue = new ConcurrentLinkedQueue<>();

	private static Queue<TableDomain> tableQueue = new ConcurrentLinkedQueue<>();
	/**
	 * 0 进行中 1 已完成
	 */
	private CountDownLatch platch = null;
	
	private CountDownLatch clatch = null;

	private DataSynDomain() {

	}

	public static DataSynDomain getInstance() {
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


}
