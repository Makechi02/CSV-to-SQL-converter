package com.makbe.converter.connections;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class Connections {

	private static Connections instance;

	private Connections() {

	}

	public static Connections getInstance() {
		if (instance == null) instance = new Connections();
		return instance;
	}

	private Properties getProperties() {
		Properties properties = new Properties();

		try(FileInputStream inputStream = new FileInputStream("database.properties")) {
			properties.load(inputStream);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

		return properties;
	}

	public Connection getConnection() {
		Properties properties = getProperties();

		String jdbcUrl = properties.getProperty("jdbc.url");
		String username = properties.getProperty("jdbc.username");
		String password = properties.getProperty("jdbc.password");

		try {
			return DriverManager.getConnection(jdbcUrl, username, password);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

}
