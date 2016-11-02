package org.apache.micro.cache.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.apache.micro.cache.domain.DataSourceDomain;
import org.apache.micro.cache.domain.StringObject;
import org.apache.micro.cache.domain.TableDomain;
import org.apache.micro.cache.model.DataSynModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DataSynProducer implements Runnable {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(DataSynProducer.class) ;

	/**
	 * 0 正在运行中
	 * 1 正常退出
	 * -1非正常退出
	 */
	private int status = 0 ;

	@Override
	public void run() {

		try {
			//如果表不在同一个数据源下的，该如何处理，修改DataSynDomain
			TableDomain domain = null ;
			while((domain = DataSynModel.getInstance().pollTableDomain()) != null){
				initData(domain);
			}
		} catch (Throwable t) {
			LOGGER.error(t.getMessage(),t) ;
		}finally{
			//如果是kill -9 异常退出时如何处理，这时整个进程已经退出
			DataSynModel.getInstance().getPlatch().countDown();
		}

	}

	private Connection getConnection(TableDomain domain) {
		DataSourceDomain dataSourceDomain = DataSynModel.getInstance().getDsMap().get(Long.valueOf(domain.getDataSourceId())) ;
		Connection connection = null;
		try {
			Class.forName(dataSourceDomain.getDriver());
			connection = DriverManager.getConnection(dataSourceDomain.getUrl(), dataSourceDomain.getUsername(),
					dataSourceDomain.getPassword());
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}finally{
			close(connection);
		}

		return connection;
	}

	private void close(Connection connection) {
		if (connection != null) {
			try {
				connection.close();
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
		}
	}

	private String getSelect(TableDomain table) {
		// 全量查询会有可能有内存溢出的问题
		String format = "select %1s from %2s";
		String select = String.format(format, table.getFields(),table.getTableName())  ;
		return select;
	}

	private void initData(TableDomain table) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = getConnection(table).prepareStatement(getSelect(table));
			rs = pstmt.executeQuery();
			while (rs.next()) {
				Map<String, String> map = new HashMap<>();

				StringObject sObject = new StringObject();
				int count = rs.getMetaData().getColumnCount();
				for (int i = 1; i <= count; i++) {
					String label = rs.getMetaData().getColumnLabel(i);
					if (table.getKeyField().equals(label)) {
						sObject.setKey(label);
					} else {
						map.put(label, rs.getString(i));
					}
				}

			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

	}

}
