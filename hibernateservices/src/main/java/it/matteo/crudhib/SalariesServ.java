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
import it.hibernate.Employees;
import it.hibernate.EmployeesHome;
import it.hibernate.Salaries;
import it.hibernate.SalariesHome;
import it.hibernate.SalariesId;
import it.hibernate.Titles;
import it.hibernate.TitlesHome;
import it.matteo.hibernatemaven.utils.InvalidInputException;
@WebService(serviceName="ServiziSalari", name="SimpleWebPortTypeSal", targetNamespace="http://simpleWeb3.it")
public class SalariesServ {
	private static final Logger log=Logger.getLogger(SalariesServ.class);
	@WebMethod
	public void insertSalariesServ(@WebParam(name="emp_no") Integer emp_no , @WebParam(name="salary")Integer salary, @WebParam(name="fromdate") Date from_date, @WebParam(name = "todate") Date to_date) {
		    Salaries s=new Salaries();
			s.setSalary(salary);
			s.setToDate(to_date);
			SalariesId id=new SalariesId(emp_no, from_date);
			s.setId(id);
			SalariesHome sh=new SalariesHome();
			sh.insertSalaries(s, emp_no, salary, from_date, to_date);
	}
	@WebMethod
	public void deleteSalariesServ(@WebParam(name="emp_no")Integer emp_no, @WebParam(name="salario")Integer s, @WebParam(name="fromdate") Date from_date, @WebParam(name="to_date") Date to_date) {
		SalariesHome eh = new SalariesHome();
		eh.removeSalary(emp_no, s, from_date, to_date);
	}
	@WebMethod
	public void updateSalariesServ(@WebParam(name="emp_no") Integer emp_no, @WebParam(name="from_date") Date from_date, @WebParam(name="salario") Integer salary, @WebParam(name="to_date")Date to_date, @WebParam(name="emp_no_new") Integer emp_no_new, @WebParam(name="salary_new") Integer salary_new, @WebParam(name="from_date_new") Date from_date_new, @WebParam(name="to_date_new")Date to_date_new ) {
		SalariesHome sh = new SalariesHome();
		sh.updateSalariesHome(emp_no, from_date, salary, to_date, emp_no_new, salary_new, from_date_new, to_date_new);
	}
	@WebMethod
	@WebResult(name="EmployeesInfo",
    targetNamespace="http://simpleWeb4.it/userInfo")
	// @XmlJavaTypeAdapter(EmployeesAdapter.class)
	public List<Salaries> readSalariesServ(@WebParam(name="EmpNo")Integer emp_no, @WebParam(name="salary")Integer salary, @WebParam(name="from_date") Date from_date, @WebParam(name="to_date")Date to_date) {
			SalariesHome th = new SalariesHome();
			return th.readSalary(emp_no,salary,from_date,to_date);
	}
}
