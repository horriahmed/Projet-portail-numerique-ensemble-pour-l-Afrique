package modele.db;

import java.sql.Connection;


import java.sql.DriverManager;

import java.sql.SQLException;

public class Database {
	public static String mysql_username="postgres";
	public static String mysql_host="93.22.122.96:5432";
	public static String mysql_password="projetsia";
	public static String mysql_db="all4africa";
	
	public static Connection getConnection() throws SQLException, ClassNotFoundException {
		//connect to PostGreSQL
		Class.forName("org.postgresql.Driver");

		Connection conn =  DriverManager.getConnection(
	        		"jdbc:postgresql://"+ mysql_host +"/"+mysql_db, mysql_username, mysql_password);

	    if (conn != null) {
	    	System.out.println("Connected to the database !");
	    } else {
	    	System.out.println("Failed to make connection !");
	    }
		return conn;
	}	
		

}
