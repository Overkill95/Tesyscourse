package it.matteo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.jws.WebService;

import org.apache.log4j.Logger;

import it.matteo.vo.DepartmentManager;
import it.matteo.vo.Departments;
//@WebService(endpointInterface="it.matteo.dao.DepartmentManagerDao")
public class DepartmentManagerDaoImpl extends Dao implements DepartmentManagerDao {
	//private static final Logger log=Logger.getLogger(Dao.class);
	public DepartmentManagerDaoImpl() {
		System.out.println("Nel costruttore di DepartmentManagerDaoImpl");
	}
	@Override
	public void insertDepartmentManager(DepartmentManager o) {
		// TODO Auto-generated method stub
		System.out.println("dentro metodo");
		// log.info("apertura connesione");
		Connection con = this.getConnection();
		System.out.println("dopo conn");
		String insert="INSERT INTO dept_manager VALUES(?,?,?,?);";
		try {
			PreparedStatement pstmt = con.prepareStatement(insert.toString());
			pstmt.setInt(1, o.getEmp_no());
			pstmt.setString(2, o.getDept_no());
			pstmt.setString(3, o.getFrom_date());
			pstmt.setString(4, o.getTo_date());
			boolean err=pstmt.execute();
			System.out.println("Query eseguita in dept manager");
			if(err) {
				System.out.println("errore");
				//log.error("Errore nell'esecuzione della insert");
				this.closeConnection();
			}
			System.out.println("Query eseguita in dep");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("errore e eccezione");
			//log.error(e);
			this.closeConnection();
	}
	
}
}
