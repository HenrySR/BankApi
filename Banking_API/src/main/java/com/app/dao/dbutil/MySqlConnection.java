package com.app.dao.dbutil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySqlConnection {

	private static Connection connection;

	public static Connection getConnection() throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		String url = "jdbc:mysql://localhost:3306/bankapi";
		String username = "root";
		String password = "Thing1110";
		connection = DriverManager.getConnection(url, username, password);
		return connection;
	}
}
//SingleTon Java Class