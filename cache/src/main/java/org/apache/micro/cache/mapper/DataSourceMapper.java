package org.apache.micro.cache.mapper;

import org.apache.base.dao.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.micro.cache.domain.DataSourceDomain;

@Mapper
public interface DataSourceMapper extends BaseMapper<DataSourceDomain>{

}
