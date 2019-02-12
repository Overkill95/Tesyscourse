package it.matteo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


import it.matteo.vo.DepartmentManager;
import it.matteo.vo.Titles;

public class TitlesDaoImpl extends Dao implements TitlesDao {
	//private static final Logger log=Logger.getLogger(Dao.class);
	@Override
	public void insertTitles(Titles o) {
		// TODO Auto-generated method stub
		//log.info("InsertTitles");
		// log.info("apertura connesione");
		Connection con = this.getConnection();
		String insert="INSERT INTO titles VALUES(?,?,?,?);";
		try {
			PreparedStatement pstmt = con.prepareStatement(insert.toString());
			pstmt.setInt(1, o.getEmpt_no());
			pstmt.setString(2, o.getTitle());
			pstmt.setString(3, o.getFrom_date());
			pstmt.setString(4, o.getTo_date());
			boolean err=pstmt.execute();
			if(err) {
				//log.error("Errore nell'esecuzione della insert");
				this.closeConnection();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			//log.error(e);
			e.printStackTrace();
				this.closeConnection();
	}
}
}