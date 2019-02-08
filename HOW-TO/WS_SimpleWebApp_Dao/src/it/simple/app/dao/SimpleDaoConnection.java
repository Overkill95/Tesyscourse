package it.simple.app.dao;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.apache.log4j.Logger;

public class SimpleDaoConnection {
	
	private static SimpleDaoConnection instance=new SimpleDaoConnection();
	private DataSource ds;
	
	private SimpleDaoConnection() {
		super();
		try {
			System.out.println("SimpleDaoConnection lookup DataSource");
			this.ds=(DataSource)new InitialContext().lookup("java:comp/env/jdbc/MySql");
			System.out.println("SimpleDaoConnection lookup DataSource OK");
			this.testConnection();
		} catch (NamingException e) {
			System.err.println("SimpleDaoConnection lookup DataSource ERROR");
			e.printStackTrace();
		}
	}
	
	public static Connection getConnection() {
		System.out.println("SimpleDaoConnection getConnection");
		try {
			return instance.ds.getConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.err.println("SimpleDaoConnection getConnection ERROR");
			e.printStackTrace();
		}
		return null;
	}
	
	private void testConnection() {
		System.out.println("SimpleDaoConnection testConnection");
		Connection c;
		try {
			c = this.ds.getConnection();
			System.out.println("Connection is alive? "+c.isValid(5));
			c.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.err.println("SimpleDaoConnection testConnection ERROR");
			e.printStackTrace();
		}

	}
}
