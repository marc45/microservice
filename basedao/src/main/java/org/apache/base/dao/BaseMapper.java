package org.apache.base.dao;

import java.util.List;

import org.apache.base.dao.provider.InsertSqlProvider;
import org.apache.base.dao.provider.SelectSqlProvider;
import org.apache.base.dao.provider.UpdateSqlProvider;
import org.apache.base.dao.table.Insert;
import org.apache.base.dao.table.Select;
import org.apache.base.dao.table.Update;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;

public abstract interface BaseMapper<T> {
	@SelectProvider(type = SelectSqlProvider.class, method = "select")
	public abstract List<T> select(Select paramSelect);

	@InsertProvider(type = InsertSqlProvider.class, method = "insert")
	public abstract int insert(Insert paramInsert);

	@UpdateProvider(type = UpdateSqlProvider.class, method = "update")
	public abstract int updateNotNullById(Update paramUpdate);

//	@UpdateProvider(type = UpdateSqlProvider.class, method = "update")
//	public abstract int updateNotNull(Update paramUpdate);
}
