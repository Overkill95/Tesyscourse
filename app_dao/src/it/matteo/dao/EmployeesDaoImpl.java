package it.matteo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;


import javax.jws.WebService;
import com.mysql.jdbc.StringUtils;

import it.matteo.vo.Departments;
import it.matteo.vo.Employees;
import it.matteo.vo.Gender;
//@WebService(endpointInterface="it.matteo.dao.DepartmentManagerDao")
public class EmployeesDaoImpl extends Dao implements EmployeesDao{
	private static final Logger log=Logger.getLogger(EmployeesDaoImpl.class);
	public EmployeesDaoImpl() {
		super();
	}
	@Override
	public void insertEmployees(Employees o) {
		//log.info("InsertEmployees");
		//log.info("apertura connesione");
		if(o==null) {
			log.error("Errore, oggetto non valido");
			return;
		}
		Connection con = this.getConnection();
		String insert="INSERT INTO employees VALUES(?,?,?,?,?,?);";
		try {
			PreparedStatement pstmt = con.prepareStatement(insert.toString());
			pstmt.setInt(1, o.getEmpt_no());
			pstmt.setString(2, o.getBirth_date());
			pstmt.setString(3, o.getFirst_name());
			pstmt.setString(4, o.getLast_name());
			pstmt.setString(5, o.getGender().toString());
			pstmt.setString(6, o.getHire_date());
			boolean err=pstmt.execute();
			log.info("Query Eseguita con successo");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			//log.error(e);
			    log.error(e);
				this.closeConnection();
				log.info("Closing connection");
		}
	}
	@Override
	public void removeEmployee(int emp_no) {
		Connection con = this.getConnection();
		String delete="DELETE FROM employees WHERE emp_no=?";
		try {
			PreparedStatement pstmt = con.prepareStatement(delete.toString());
			pstmt.setInt(1, emp_no);
			boolean err=pstmt.execute();
			log.info("insert Eseguita con successo");
		}
		catch(SQLException ex) {
			log.error(ex);
			this.closeConnection();
			log.info("Closing connection");
		}
	}
	@Override
	public  void updateEmployee(int pk, Integer emp_no, String bd, String fn, String ln, String g, String hd) {
		Connection con=this.getConnection();
		try {
			String update=constructString(emp_no, bd, fn, ln, g, hd);
			PreparedStatement pstmt = con.prepareStatement(update);
			boolean ret=execUpdate(pstmt, emp_no, bd, fn, ln, g, hd, pk);
			if(!ret) {
				log.error("Errore nell'esecuzione della update");
				this.closeConnection();
			}
			log.info("update Eseguita con successo");
		}
		catch(SQLException ex) {
			log.error(ex);
			this.closeConnection();
			log.info("Closing connection");
			return;
		}
	}
	@Override
	public List<Employees> selectEmployee(Integer emp_no, String bd, String fn, String ln, String g, String hd) {
		// TODO Auto-generated method stub
		ArrayList<Employees> result=new ArrayList<Employees>();
		try {
			//log.info("getDepartments");
			//log.info("apertura connesione");
			Connection con = this.getConnection();
			boolean emp_no_bool=emp_no!=null;
			boolean bd_bool = bd!=null;
			boolean fn_bool=fn!=null;
			boolean ln_bool=ln!=null;
			boolean gn_bool=g!=null;
			boolean hd_bool=hd!=null;
			StringBuilder sql = new StringBuilder("SELECT * FROM employees.employees WHERE 1=1 ");
			if(emp_no_bool)
				sql.append("AND emp_no = ?");
			if (bd_bool)
				sql.append("AND birth_date = ?");
			if (fn_bool)
				sql.append("AND first_name = ?");
			if (ln_bool)
				sql.append("AND last_name = ?");
			if(gn_bool)
				sql.append("AND gender = ?");
			if(hd_bool)
				sql.append("AND hire_date = ?");
			PreparedStatement pstmt = con.prepareStatement(sql.toString());
			int index = 1;
			if(emp_no_bool)
				pstmt.setInt(index++, emp_no);
			if (bd_bool)
				pstmt.setString(index++, bd);
			if (fn_bool)
				pstmt.setString(index++, fn);
			if (ln_bool)
				pstmt.setString(index++, ln);
			if (gn_bool)
				pstmt.setString(index++, g);
			if (hd_bool)
				pstmt.setString(index++, hd);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				result.add(new Employees(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6)));
			}
			log.info("Query Eseguita con successo");
		}
		catch (Exception ex1) {
			log.error(ex1);
		}
		return result;
	}
	public static String constructString(Integer emp_no, String bd, String fn, String ln, String g, String hd) {
		String query="UPDATE employees";
		if(emp_no!=null) {
			query += " SET emp_no=?";
		}
		if(bd!=null) {
			query +=", birth_date=?";
		}
		if(fn!=null) {
			query +=", first_name=?";
		}
		if(ln!=null) {
			query += ", last_name=?";
		}
		if(g!=null) {
			query += ", gender=?";
		}
		if(hd!=null) {
			query += ", hire_date=?";
		}
		return query += " WHERE emp_no=?";
	}
	public boolean execUpdate(PreparedStatement st, Integer emp_no, String bd, String fn, String ln, String g, String hd, int pk) {
		String query="UPDATE employees";
		try {
			
			if(emp_no!=null) {
				st.setInt(1, emp_no);
			}
			if(bd!=null) {
				st.setString(2, bd);
			}
			if(fn!=null) {
				st.setString(3, fn);
			}
			if(ln!=null) {
				st.setString(4, ln);
			}
			if(g!=null) {
				st.setString(5, g);
			}
			if(hd!=null) {
				st.setString(6, hd);
			}
			st.setInt(7, pk);
			return st.execute();
		}
		catch(SQLException e) {
			log.error(e);
			this.closeConnection();
			return false;
		}
	}
}