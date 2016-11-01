package org.apache.base.service.impl;

import java.util.List;

import org.apache.base.dao.table.Insert;
import org.apache.base.dao.table.Update;
import org.apache.base.service.BaseService;

public abstract class BaseServiceImpl<T> implements BaseService<T> {

	@Override
	public int save(T domain) {
		Insert insert = Insert.newInsert(domain);
		int reflect = getMapper().insert(insert);
		return reflect;
	}

	@Override
	public int updateNotNull(T domain) {
		Update update = Update.newUpdate(domain, true);
		int reflect = this.getMapper().update(update);
		return reflect;
	}

	@Override
	public int delete(T domain) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	

	@Override
	public List<T> selectAll() {
		List<T> domains = this.select(null) ;
		return domains;
	}

	@Override
	public List<T> select(T domain, int start, int pageSize) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<T> select(T domain) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public T selectOne(T domain) {
		// TODO Auto-generated method stub
		return null;
	}

}
