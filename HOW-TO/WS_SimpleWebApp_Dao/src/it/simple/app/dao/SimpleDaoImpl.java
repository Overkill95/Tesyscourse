package it.simple.app.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.mysql.jdbc.StringUtils;

import it.simple.app.dto.UserInfo;

public class SimpleDaoImpl implements SimpleDaoInterface {

	private Connection c;
	private static final Logger log=Logger.getLogger(SimpleDaoImpl.class);
	
	public SimpleDaoImpl() {
		c=SimpleDaoConnection.getConnection();
	}
	
	private static UserInfo makeUserInfo(ResultSet rs) throws SQLException {
		return new UserInfo(
		rs.getString(	"Host"	),
		rs.getString(	"User"	),
		rs.getString(	"Select_priv"	),
		rs.getString(	"Insert_priv"	),
		rs.getString(	"Update_priv"	),
		rs.getString(	"Delete_priv"	),
		rs.getString(	"Create_priv"	),
		rs.getString(	"Drop_priv"	),
		rs.getString(	"Reload_priv"	),
		rs.getString(	"Shutdown_priv"	),
		rs.getString(	"Process_priv"	),
		rs.getString(	"File_priv"	),
		rs.getString(	"Grant_priv"	),
		rs.getString(	"References_priv"	),
		rs.getString(	"Index_priv"	),
		rs.getString(	"Alter_priv"	),
		rs.getString(	"Show_db_priv"	),
		rs.getString(	"Super_priv"	),
		rs.getString(	"Create_tmp_table_priv"	),
		rs.getString(	"Lock_tables_priv"	),
		rs.getString(	"Execute_priv"	),
		rs.getString(	"Repl_slave_priv"	),
		rs.getString(	"Repl_client_priv"	),
		rs.getString(	"Create_view_priv"	),
		rs.getString(	"Show_view_priv"	),
		rs.getString(	"Create_routine_priv"	),
		rs.getString(	"Alter_routine_priv"	),
		rs.getString(	"Create_user_priv"	),
		rs.getString(	"Event_priv"	),
		rs.getString(	"Trigger_priv"	),
		rs.getString(	"Create_tablespace_priv"	),
		rs.getString(	"ssl_type"	));
	}
	
	@Override
	public List<UserInfo> getUserInfo(String name) {
		List<UserInfo> l=new ArrayList<UserInfo>();
		try {
		log.info("SimpleDaoInterface getUserInfo");
		StringBuilder sql=new StringBuilder("select Host,User,Select_priv,Insert_priv,Update_priv,Delete_priv,Create_priv,Drop_priv,Reload_priv,Shutdown_priv,Process_priv,File_priv,Grant_priv,References_priv,Index_priv,Alter_priv,Show_db_priv,Super_priv,Create_tmp_table_priv,Lock_tables_priv,Execute_priv,Repl_slave_priv,Repl_client_priv,Create_view_priv,Show_view_priv,Create_routine_priv,"
				+ "Alter_routine_priv,Create_user_priv,Event_priv,Trigger_priv,Create_tablespace_priv, ssl_type from mysql.user ");
		if(!StringUtils.isNullOrEmpty(name))
			sql.append("where User = ?");
		PreparedStatement p=c.prepareStatement(sql.toString());
		if(!StringUtils.isNullOrEmpty(name))
			p.setString(1, name);
		p.execute();
		ResultSet rs=p.getResultSet();
		while(rs.next())
			l.add(makeUserInfo(rs));
		}
		catch (Exception e) {
			log.error("SimpleDaoInterface getUserInfo ERROR",e);
		}
		finally {
			try {
				if(c!=null&&!c.isClosed())
				c.close();
			} catch (SQLException e) {
				log.error("connection close error",e);
			}
		}
		return l;
	}

}
