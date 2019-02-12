package it.matteo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import java.sql.Statement;
import it.matteo.connection.ConnectionManager;
import it.matteo.vo.DepartmentManager;
import it.matteo.vo.Departments;

public class Dao{
	private Connection con=null;
	//private static final Logger log=Logger.getLogger(Dao.class);
	protected Dao() {
		try {
			System.out.println("aaaa1");
			this.con=ConnectionManager.getConnectionToDatabase();
			System.out.println("aaaa12");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("INel eccez");
			e.printStackTrace();
		}
	}

	protected Connection getConnection(){
		return con;
	}
	public void closeConnection(){
		try {
			if(con!=null&&!con.isClosed())
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/*public void countTables() {
		// TODO Auto-generated method stub
		Connection con = this.getConnection();
		String deps="SELECT COUNT(*) FROM departments";
		String emps="SELECT COUNT(*) FROM employees";
		String dept_emp="SELECT COUNT(*) FROM dept_emp";
		String dept_man="SELECT COUNT(*) FROM dept_manager";
		String salaries="SELECT COUNT(*) FROM salaries";
		String titles="SELECT COUNT(*) FROM titles";
		try {
			Statement st1 = con.createStatement();
			Statement st2 = con.createStatement();
			Statement st3 = con.createStatement();
			Statement st4 = con.createStatement();
			Statement st5 = con.createStatement();
			Statement st6 = con.createStatement();
			ResultSet rs1=st1.executeQuery(deps);
			int c1=0;
			int c2=0;
			int c3=0;
			int c4=0;
			int c5=0;
			int c6=0;
			while(rs1.next()) {
				c1=rs1.getInt(1);
			}
			rs1.close();
			st1.close();
			ResultSet rs2=st2.executeQuery(emps);
			while(rs2.next()) {
				c2=rs2.getInt(1);
			}
			rs2.close();
			st2.close();
			ResultSet rs3=st3.executeQuery(dept_emp);
			while(rs3.next()) {
				c3=rs3.getInt(1);
			}
			rs3.close();
			st3.close();
			ResultSet rs4=st4.executeQuery(dept_man);
			while(rs4.next()) {
				c4=rs4.getInt(1);
			}
			rs4.close();
			st4.close();
			ResultSet rs5=st5.executeQuery(salaries);
			while(rs5.next()) {
				c5=rs5.getInt(1);
			}
			rs5.close();
			st5.close();
			ResultSet rs6=st6.executeQuery(titles);
			while(rs6.next()) {
				c6=rs6.getInt(1);
			}
			rs6.close();
			st6.close();
			log.info("Numero impiegati caricati: \n" + c2 + "Numero Dipartimenti caricati: \n" + c1 +  "Numero Salari caricati: \n" + c5 + "Numero Titoli caricati: \n" + c6 + "Numero Manager caricati: \n" + c4 + "Lavori per dipartimento caricati: \n" + c3 + "\n");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			log.error(e);
			this.closeConnection();
			return;
	}
	
}
*/	
}
