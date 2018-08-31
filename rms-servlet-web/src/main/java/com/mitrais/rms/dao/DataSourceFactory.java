package com.mitrais.rms.dao;



import javax.sql.DataSource;

import com.mysql.cj.jdbc.MysqlDataSource;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

/**
 * This class provides MySQL datasource to be used to connect to database.
 * It implements singleton pattern See <a href="http://www.oodesign.com/singleton-pattern.html">Singleton Pattern</a>
 */
public class DataSourceFactory
{
    private final DataSource dataSource;

    DataSourceFactory()
    {
        MysqlDataSource dataSource = new MysqlDataSource();
        Properties prop = new Properties();
        InputStream input = null;
        try {
			input = new FileInputStream("src/main/resources/database.properties");
			prop.load(input);
			dataSource.setDatabaseName(prop.getProperty("DB_SCHEMA"));
	        dataSource.setServerName(prop.getProperty("DB_HOST"));
	        dataSource.setPort(Integer.parseInt(prop.getProperty("DB_PORT")));
	        dataSource.setUser(prop.getProperty("DB_USER"));
	        dataSource.setPassword(prop.getProperty("DB_PASSWORD"));
		} catch (IOException  e) {
			e.printStackTrace();
		} finally {
			if(input != null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
        this.dataSource = dataSource;
    }

    /**
     * Get a data source to database
     *
     * @return DataSource object
     */
    public static Connection getConnection() throws SQLException
    {
        return SingletonHelper.INSTANCE.dataSource.getConnection();
    }

    private static class SingletonHelper
    {
        private static final DataSourceFactory INSTANCE = new DataSourceFactory();
    }
}