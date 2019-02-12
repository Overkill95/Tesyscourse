package it.matteo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.jws.WebService;

import it.matteo.vo.DeptEmp;
//@WebService(endpointInterface="it.matteo.dao.DeptEmpDao")
public class DeptEmpDaoImpl extends Dao implements DeptEmpDao{
	//private static final Logger log=Logger.getLogger(Dao.class);

	@Override
	public void insertDeptEmp(DeptEmp o) {
		// TODO Auto-generated method stub
		//log.info("InsertDepartmentsEmpt");
		//log.info("apertura connesione");
		Connection con = this.getConnection();
		String insert="INSERT INTO dept_emp VALUES(?,?,?,?);";
		try {
			PreparedStatement pstmt = con.prepareStatement(insert.toString());
			pstmt.setInt(1, o.getEmp_no());
			pstmt.setString(2, o.getDept_no());
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
				this.closeConnection();
	}
	}
}
