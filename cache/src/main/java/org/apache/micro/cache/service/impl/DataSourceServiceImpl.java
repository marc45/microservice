package org.apache.micro.cache.service.impl;

import java.util.List;

import org.apache.base.dao.BaseMapper;
import org.apache.base.dao.table.Select;
import org.apache.base.service.impl.BaseServiceImpl;
import org.apache.micro.cache.domain.DataSourceDomain;
import org.apache.micro.cache.mapper.DataSourceMapper;
import org.apache.micro.cache.service.DataSourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DataSourceServiceImpl extends BaseServiceImpl<DataSourceDomain> implements DataSourceService {
	
	@Autowired
	private DataSourceMapper dataSourceMapper ;

	@Override
	public BaseMapper<DataSourceDomain> getMapper() {
		return dataSourceMapper ;
	}

	@Override
	public List<DataSourceDomain> select(DataSourceDomain domain,int start,int pageSize) {
		Select select = Select.newSelect(domain.getClass()).limit(start,pageSize) ;
		
		List<DataSourceDomain> domains = this.getMapper().select(select) ;
		return domains;
	}
	
	
	

	@Override
	public DataSourceDomain selectOne(DataSourceDomain domain) {
		
		
		
		return null ;
	}
	
	

}
