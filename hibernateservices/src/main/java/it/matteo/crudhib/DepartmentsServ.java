package it.matteo.crudhib;

import java.sql.Date;
import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

import org.apache.log4j.Logger;

import it.hibernate.Departments;
import it.hibernate.DepartmentsHome;
import it.hibernate.DepartmentsOutput;
import it.matteo.hibernatemaven.utils.InvalidInputException;

@WebService(serviceName="ServiziDipartimenti", name="SimpleWebPortTypeDep",
targetNamespace="http://simpleWeb5.it")
public class DepartmentsServ {
	private static final Logger log=Logger.getLogger(DepartmentsServ.class);
	@WebMethod
	public void insertDepartmentServ(@WebParam(name="dept_no") String dept_no, @WebParam(name="deptName")String deptName) {
		    Departments d;
			d = new Departments();
			d.setDeptNo(dept_no);
			d.setDeptName(deptName);
			DepartmentsHome ed=new DepartmentsHome();
			ed.insertDepartment(d);
	}
	@WebMethod
	public void deleteDepartmentsServ(@WebParam(name="dept_no")String dept_no, @WebParam(name="dept_name") String dept_name) {
		DepartmentsHome ed = new DepartmentsHome();
		ed.removeDepartment(dept_no, dept_name);
	}
	@WebMethod
	public void updateDepartmentServ(@WebParam(name="dept_no") String dept_no, @WebParam(name="dept_name")String dept_name, @WebParam(name="dept_no_new")String dept_no_new, @WebParam(name="dept_name_new")String dept_name_new ) {
		DepartmentsHome ed = new DepartmentsHome();
		try {
			ed.updateDepartments(dept_no,dept_name,dept_no_new,dept_name_new );
		} catch (InvalidInputException e) {
			log.error(e);
			return;
		}
	}
	@WebMethod
	@WebResult(name="EmployeesInfo",
    targetNamespace="http://simpleWeb5.it/userInfo")
	// @XmlJavaTypeAdapter(EmployeesAdapter.class)
	public List<DepartmentsOutput> readDepartmentsServ(@WebParam(name="DeptNo")String dept_no, @WebParam(name="deptName")String deptName) {
			DepartmentsHome ed = new DepartmentsHome();
			return ed.readDepartments(dept_no, deptName);
	}
	@WebMethod
	public void InsertEmployeeToDepart(@WebParam(name="dept_no")String dept_no, @WebParam(name="emp_id") Integer emp_id, @WebParam(name="from_date")Date from_date, @WebParam(name="to_date")Date to_date) {
		DepartmentsHome ed = new DepartmentsHome();
		try {
			ed.insertEmployeeToDepart(dept_no, emp_id, from_date, to_date);
		} catch (InvalidInputException e) {
			log.error(e);
			// TODO Auto-generated catch block
			return;
		}
	}
}
