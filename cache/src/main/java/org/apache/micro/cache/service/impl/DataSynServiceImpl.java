package org.apache.micro.cache.service.impl;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.apache.micro.cache.domain.DataSourceDomain;
import org.apache.micro.cache.domain.TableDomain;
import org.apache.micro.cache.model.DataSynModel;
import org.apache.micro.cache.service.DataSourceService;
import org.apache.micro.cache.service.DataSynConsumer;
import org.apache.micro.cache.service.DataSynProducer;
import org.apache.micro.cache.service.DataSynService;
import org.apache.micro.cache.service.TableService;

public class DataSynServiceImpl implements DataSynService {
	
	private DataSourceService dataSourceService ;
	
	private TableService tableService ;
	
	private static Lock lock = new ReentrantLock() ;
	
	@Override
	public void init(String table) {
		
		lock.lock();
		try{
			if(DataSynModel.getInstance().getDsMap().size() == 0){
				List<DataSourceDomain> dataSources = dataSourceService.selectAll() ;
				for(DataSourceDomain dataSource : dataSources){
					DataSynModel.getInstance().getDsMap().put(Long.valueOf(dataSource.getId()), dataSource) ;
				}
				
			}
			
			int producerThreads = -1 ;
			int consumerThreads = -1 ;
			List<TableDomain> tables = null ;
			if(table != null && !"".equals(table)){
				tables = tableService.selectAll() ;
				
				producerThreads = 5 ;
				consumerThreads = 5 ;
			}else{
				TableDomain tableDomain = new TableDomain() ;
				tableDomain.setTableName(table);
				tables = tableService.select(tableDomain) ;
				
				producerThreads = 1 ;
				consumerThreads = 1 ;
			}
			DataSynModel.getInstance().addTableDomains(tables); 
			
			ExecutorService producerService = Executors.newFixedThreadPool(producerThreads) ;
			for(int i = 0 ;i < producerThreads ;i++){
				producerService.submit(new DataSynProducer()) ;
			}
			
			ExecutorService consumerService = Executors.newFixedThreadPool(consumerThreads) ;
			for(int i = 0 ;i < consumerThreads ;i++){
				producerService.submit(new DataSynConsumer()) ;
			}
			
			producerService.shutdown(); 
			consumerService.shutdown();
		}finally{
			lock.unlock(); 
		}
		
	}


}
