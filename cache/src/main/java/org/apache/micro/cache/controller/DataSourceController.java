package org.apache.micro.cache.controller;

import java.util.List;

import org.apache.micro.cache.domain.DataSourceDomain;
import org.apache.micro.cache.service.DataSourceService;
import org.apache.micro.cache.service.impl.IdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class DataSourceController {

	@Autowired
	private DataSourceService dataSourceService ;
	
	private IdService idService ;
	
	public List<DataSourceDomain> find(String dsName,int start,int pageSize){
		DataSourceDomain req = new DataSourceDomain() ;
		if(dsName != null && !"".equals(dsName)){
			req.setDsName(dsName);
		}
		List<DataSourceDomain> dsources = dataSourceService.select(req, start, pageSize) ;
		return dsources ;
	}
	
	public int update(DataSourceDomain ds){
		int result = dataSourceService.updateNotNullById(ds) ;
		return result ;
	}
	
	public int insert(DataSourceDomain ds){
		ds.setId(idService.nextId());
		int result = dataSourceService.save(ds) ;
		return result ;
	}
}
