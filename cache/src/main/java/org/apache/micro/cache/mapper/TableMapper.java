package org.apache.micro.cache.mapper;

import org.apache.base.dao.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.micro.cache.domain.TableDomain;

@Mapper
public interface TableMapper extends BaseMapper<TableDomain>{

}
