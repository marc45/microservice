package org.apache.base.service;

import java.util.List;

import org.apache.base.dao.Mapper;

public interface BaseService<T> {

	public Mapper<T> getMapper() ;
	
	public int save(T domain);
	
	public int updateNotNullById(T domain);
	
	public int delete(T domain);
	
	public List<T> selectAll() ;
	
	public List<T> select(T domain,int start,int pageSize) ;
	
	public List<T> select(T domain);
	
	public T selectOne(T domain)  ;
}
