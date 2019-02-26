package it.matteo.crudhib;
import java.sql.Date;
import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

import org.apache.log4j.Logger;

import it.hibernate.Employees;
import it.hibernate.EmployeesHome;
import it.hibernate.EmployeesOutput;
@WebService(serviceName="ServiziImpiegati2", name="SimpleWebPortType2",
targetNamespace="http://simpleWeb3.it")
public class EmployeesServ {
	private static final Logger log=Logger.getLogger(EmployeesServ.class);
	@WebMethod
	public void insertEmployeeServ(@WebParam(name="emp_no") Integer emp_no , @WebParam(name="birth_date")Date birth_date, @WebParam(name="first_name") String first_name, @WebParam(name="last_name") String last_name, @WebParam(name="gender")String g, @WebParam(name="hire_date")Date hire_date) {
		    Employees e=new Employees();
			e.setEmpNo(emp_no);
			e.setBirthDate(birth_date);
			e.setFirstName(first_name);
			e.setLastName(last_name);
			e.setGender(g);
			e.setHireDate(hire_date);
			EmployeesHome eh=new EmployeesHome();
			eh.insertEmployees(e);
	}
	@WebMethod
	public void deleteEmployeesServ(@WebParam(name="emp_no")Integer emp_no, @WebParam(name="birth_date")Date birth_date, @WebParam(name="first_name") String first_name, @WebParam(name="last_name") String last_name, @WebParam(name="gender") String g, @WebParam(name="hire_date")Date hd) {
		EmployeesHome eh = new EmployeesHome();
		eh.removeEmployee(emp_no, birth_date, first_name, last_name, g, hd);
	}
	@WebMethod
	public void updateEmployeesServ(@WebParam(name="emp_no") Integer emp_no, @WebParam(name="birth_date")Date bd, @WebParam(name="first_name")String fn, @WebParam(name="last_name") String ln, @WebParam(name="sesso") String g, @WebParam(name="data_assunzione")Date hire_date, @WebParam(name="emp_no_new") Integer emp_no_new, @WebParam(name="data_nascita_nuova") Date bd_new, @WebParam(name="nome_nuovo") String fn_new, @WebParam(name="cognome_nuovo") String ln_new, @WebParam(name="sesso_nuovo") String g_new, @WebParam(name="data_assunzione_nuova") Date hd_new ) {
		EmployeesHome ed = new EmployeesHome();
	    ed.updateEmployee(emp_no, bd, fn, ln, g, hire_date, emp_no_new, bd_new, fn_new, ln_new, g_new, hd_new);
	}
	@WebMethod
	@WebResult(name="EmployeesInfo",
    targetNamespace="http://simpleWeb4.it/userInfo")
	// @XmlJavaTypeAdapter(EmployeesAdapter.class)
	public List<EmployeesOutput> readEmployeesServ(@WebParam(name="EmpNo")Integer emp_no, @WebParam(name="birthDate")Date birth_date, @WebParam(name="first_name") String first_name, @WebParam(name="last_name")String last_name, @WebParam(name="gender") String g, @WebParam(name="hire_date") Date hire_date) {
			EmployeesHome ed = new EmployeesHome();
			return ed.readEmployees(emp_no, birth_date, first_name, last_name, g, hire_date);
	}
}
