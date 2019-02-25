package it.matteo.crudhib;

import java.sql.Date;
import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

import org.apache.log4j.Logger;
import it.hibernate.*;

@WebService(serviceName="ServiziDipMan", name="SimpleWebPortTypeDeptMan",
targetNamespace="http://simpleWeb4.it")
public class DeptManServ {
	private static final Logger log=Logger.getLogger(DeptManServ.class);
	@WebMethod
	public void insertDeptManServ(@WebParam(name="dept_no") String dept_no, @WebParam(name="emp_no")Integer emp_no, @WebParam(name="from_date")Date from_date, @WebParam(name="to_date")Date to_date) {
		    DeptManager d;
			d = new DeptManager();
			d.setFromDate(from_date);
			d.setToDate(to_date);
			DeptManagerHome ed=new DeptManagerHome();
			ed.insertDeptMan(d, dept_no, emp_no);
	}
	@WebMethod
	public void deleteDeptManServ(@WebParam(name="dept_no")String dept_no, Integer emp_no) {
		DeptManagerHome ed = new DeptManagerHome();
		ed.removeDeptManager(dept_no,emp_no);
	}
	@WebMethod
	public void updateDeptManServ(@WebParam(name="dept_no") String dept_no, @WebParam(name="emp_no") Integer emp_no, @WebParam(name="from_date")Date from_date, @WebParam(name="to_date")Date to_date, @WebParam(name="dept_no_nuovo")String dept_no_new, @WebParam(name="emp_no_nuovo")Integer emp_no_new, @WebParam(name="from_date_new") Date from_date_new, @WebParam(name="to_date_new")Date to_date_new) {
		DeptManagerHome ed = new DeptManagerHome();
		ed.updateDeptManHome(dept_no,emp_no,from_date, to_date, dept_no_new,emp_no_new,from_date_new, to_date_new);
	}
	@WebMethod
	@WebResult(name="DeptEmpInfo",
    targetNamespace="http://simpleWeb4.it/userInfo")
	// @XmlJavaTypeAdapter(EmployeesAdapter.class)
	public List<DeptEmp> readDeptManServ(@WebParam(name="DeptNo")String dept_no, @WebParam(name="empNo")Integer emp_no, @WebParam(name="fromdate") String from_date, @WebParam(name="todate") String to_date) {
			DeptEmpHome ed = new DeptEmpHome();
			return ed.readDeptEmp(dept_no, emp_no, from_date, to_date);
	}
}