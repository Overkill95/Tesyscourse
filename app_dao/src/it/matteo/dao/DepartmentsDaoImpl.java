package it.matteo.dao;

import java.util.List;

import javax.jws.WebService;

import org.apache.log4j.Logger;

import java.util.*;

import com.mysql.jdbc.StringUtils;

import java.sql.*;

import it.matteo.vo.Departments;
//@WebService(endpointInterface="it.matteo.dao.DepartmentsDao")
public class DepartmentsDaoImpl extends Dao implements DepartmentsDao {

	//private static final Logger log=Logger.getLogger(Dao.class);
	
	public DepartmentsDaoImpl() {
		super();
	}

	public List<Departments> getDepartments(String dept_no, String dept_name) {
		ArrayList<Departments> result=new ArrayList<Departments>();
		try {
			//log.info("getDepartments");
			//log.info("apertura connesione");
			Connection con = this.getConnection();
			boolean dept_no_bool = !StringUtils.isNullOrEmpty(dept_no);
			boolean dept_name_bool = !StringUtils.isNullOrEmpty(dept_name);
			StringBuilder sql = new StringBuilder("SELECT d.dept_name, d.dept_no FROM employees.departments d WHERE 1=1 ");
			if (dept_no_bool)
				sql.append("AND d.dept_no = ?");
			if (dept_name_bool)
				sql.append("AND d.dept_name = ?");
			PreparedStatement pstmt = con.prepareStatement(sql.toString());
			int index = 1;
			if (dept_no_bool)
				pstmt.setString(index++, dept_no);
			if (dept_name_bool)
				pstmt.setString(index, dept_name);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				result.add(new Departments(rs.getString(2), rs.getString(1)));
			}
			//log.info("chiusura connesione");
		}
		catch (Exception e) {
			e.printStackTrace();
			//log.error(e);
			this.closeConnection();
		}
		return result;
	}

	public void insertDepartments(Departments o) {
		//log.info("InsertDepartments");
		//log.info("apertura connesione");
		Connection con = this.getConnection();
		String insert="INSERT INTO departments VALUES(?,?);";
		try {
			PreparedStatement pstmt = con.prepareStatement(insert.toString());
			pstmt.setString(1, o.getDept_no());
			pstmt.setString(2, o.getDept_name());
			boolean err=pstmt.execute();
			if(err) {
				//log.error("Errore nell'esecuzione della insert");
				this.closeConnection();
			}
			System.out.println("Query eseguita in depart");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			//log.error(e);
			this.closeConnection();
		}
}
}
