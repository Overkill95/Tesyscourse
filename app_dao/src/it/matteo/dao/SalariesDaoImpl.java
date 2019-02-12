package it.matteo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


import it.matteo.vo.Employees;
import it.matteo.vo.Salaries;

public class SalariesDaoImpl extends Dao implements SalariesDao{
	//private static final Logger log=Logger.getLogger(Dao.class);
	public void insertSalaries(Salaries o) {
		//log.info("InsertSalaries");
		// log.info("apertura connesione");
		Connection con = this.getConnection();
		String insert="INSERT INTO salaries VALUES(?,?,?,?);";
		try {
			PreparedStatement pstmt = con.prepareStatement(insert.toString());
			pstmt.setInt(1, o.getEmpt_no());
			pstmt.setInt(2, o.getSalary());
			pstmt.setString(3, o.getFrom_date());
			pstmt.setString(4, o.getTo_date());
			boolean err=pstmt.execute();
			if(err) {
				//log.error("Errore nell'esecuzione della insert");
				this.closeConnection();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			this.closeConnection();
		}
	}

}
