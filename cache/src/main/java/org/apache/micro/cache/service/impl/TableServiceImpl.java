package org.apache.micro.cache.service.impl;

import org.apache.base.dao.BaseMapper;
import org.apache.base.service.impl.BaseServiceImpl;
import org.apache.micro.cache.domain.TableDomain;
import org.apache.micro.cache.mapper.TableMapper;
import org.apache.micro.cache.service.TableService;
import org.springframework.beans.factory.annotation.Autowired;

public class TableServiceImpl extends BaseServiceImpl<TableDomain> implements TableService {
	
	@Autowired
	private TableMapper tableMapper ;

	@Override
	public BaseMapper<TableDomain> getMapper() {
		return tableMapper;
	}

}
