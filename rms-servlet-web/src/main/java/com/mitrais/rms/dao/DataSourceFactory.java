package com.mitrais.rms.dao;

import javax.sql.DataSource;

import com.mysql.cj.jdbc.MysqlDataSource;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ResourceBundle;

/**
 * This class provides MySQL datasource to be used to connect to database. It
 * implements singleton pattern See
 * <a href="http://www.oodesign.com/singleton-pattern.html">Singleton
 * Pattern</a>
 */
public class DataSourceFactory {
	private final DataSource dataSource;

	DataSourceFactory() {
		MysqlDataSource dataSource = new MysqlDataSource();
		ResourceBundle prop = ResourceBundle.getBundle("database");
		dataSource.setDatabaseName(prop.getString("DB_SCHEMA"));
		dataSource.setServerName(prop.getString("DB_HOST"));
		dataSource.setPort(Integer.valueOf(prop.getString("DB_PORT")));
		dataSource.setUser(prop.getString("DB_USER"));
		dataSource.setPassword(prop.getString("DB_PASSWORD"));
		this.dataSource = dataSource;
	}

	/**
	 * Get a data source to database
	 *
	 * @return DataSource object
	 */
	public static Connection getConnection() throws SQLException {
		return SingletonHelper.INSTANCE.dataSource.getConnection();
	}

	private static class SingletonHelper {
		private static final DataSourceFactory INSTANCE = new DataSourceFactory();
	}
}