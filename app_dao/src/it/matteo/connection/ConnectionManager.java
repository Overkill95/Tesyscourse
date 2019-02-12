package it.matteo.connection;

import java.sql.*;
import java.util.Properties;

import javax.annotation.Resource;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import it.matteo.dao.Dao;

public class ConnectionManager {
	//private static final Logger log=Logger.getLogger(ConnectionManager.class);
	@Resource(name="jdbc/TestDB")
    private javax.sql.DataSource dsc;
	
	private ConnectionManager() {
		
	}
	public static Connection getConnectionToDatabase() throws SQLException {
		System.out.println("conn manager");
		try {
            Context initContext = new InitialContext();
            Context envContext = (Context) initContext.lookup("java:/comp/env");
            DataSource datasource = (DataSource) envContext.lookup("jdbc/MySql");
            Connection con = datasource.getConnection();
            //System.out.println("conn ssss");
            return con;
		}
		catch(NamingException ne) {
			ne.printStackTrace();
			System.out.println("errore e eccezione2");
			//log.error(ne);
			return null;
		}
	    
	}
}
