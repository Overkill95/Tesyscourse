package it.matteo.crudhib;

import java.sql.Date;
import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

import org.apache.log4j.Logger;
import it.hibernate.*;

@WebService(serviceName="ServiziDipImpiegati", name="SimpleWebPortTypeDeptEmp",
targetNamespace="http://simpleWeb4.it")
public class DeptEmpServ {
	private static final Logger log=Logger.getLogger(EmployeesServ.class);
	@WebMethod
	public void insertDeptEmpServ(@WebParam(name="dept_no") String dept_no, @WebParam(name="emp_no")Integer emp_no, @WebParam(name="from_date")Date from_date, @WebParam(name="to_date")Date to_date) {
		    DeptEmp d;
			d = new DeptEmp();
			d.setFromDate(from_date);
			d.setToDate(to_date);
			DeptEmpHome ed=new DeptEmpHome();
			ed.insertDeptEmp(d, dept_no, emp_no);
	}
	@WebMethod
	public void deleteDeptEmpServ(@WebParam(name="dept_no")String dept_no, Integer emp_no) {
		DeptEmpHome ed = new DeptEmpHome();
		ed.removeDeptEmp(dept_no,emp_no);
	}
	@WebMethod
	public void updateDeptEmpServ(@WebParam(name="dept_no") String dept_no, @WebParam(name="emp_no") Integer emp_no, @WebParam(name="from_date") Date from_date, @WebParam(name="to_date")Date to_date, @WebParam(name="dept_no_nuovo")String dept_no_new, @WebParam(name="emp_no_nuovo")Integer emp_no_new, @WebParam(name="from_date_new")Date from_date_new, @WebParam(name="to_date_new")Date to_date_new) {
		DeptEmpHome ed = new DeptEmpHome();
		ed.updateDeptHome(dept_no,emp_no, from_date, to_date,dept_no_new,emp_no_new, from_date_new, to_date_new);
	}
	@WebMethod
	@WebResult(name="DeptEmpInfo",
    targetNamespace="http://simpleWeb4.it/userInfo")
	// @XmlJavaTypeAdapter(EmployeesAdapter.class)
	public List<DeptEmp> readDeptEmpServ(@WebParam(name="DeptNo")String dept_no, @WebParam(name="empNo")Integer emp_no, @WebParam(name="fromdate") String from_date, @WebParam(name="todate") String to_date) {
			DeptEmpHome ed = new DeptEmpHome();
			return ed.readDeptEmp(dept_no, emp_no, from_date, to_date);
	}
}
