package it.matteo.crudhib;
import java.util.*;

import javax.jws.*;
import org.apache.log4j.Logger;
import java.sql.Date;

import it.matteo.dao2.*;
import it.matteo.utils2.Gender;
import it.matteo.utils2.InvalidInputException;
import it.matteo.vo2.Employees;

import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
@WebService(serviceName="ServiziImpiegati2", name="SimpleWebPortType2",
targetNamespace="http://simpleWeb3.it")
public class EmployeesServ {
	private static final Logger log=Logger.getLogger(EmployeesServ.class);
	@WebMethod
	public void insertEmployeeServ(@WebParam(name="emp_no")int emp_no, @WebParam(name="data_nascita")Date bd, @WebParam(name="nome")String fn, @WebParam(name="cognome")String ln, @WebParam(name="sesso")String g, @WebParam(name="data_assunzione")Date hd) {
		Employees e;
		try {
			e = new Employees();
			e.setEmpt_no(emp_no);
			if(bd==null) System.out.println("birth date null");
			e.setBirth_date(bd);
			e.setFirst_name(fn);
			e.setLast_name(ln);
			e.setGender(Gender.toGender(g));
			e.setHire_date(hd);
			EmployeesDao ed=new EmployeesDaoImpl();
			ed.insertEmployees(e);
		} catch (InvalidInputException e1) {
			// TODO Auto-generated catch block
			log.error(e1);
			return;
		}
	}
	@WebMethod
	public void deleteEmployeeServ(@WebParam(name="emp_no")Integer emp_no) {
		EmployeesDao ed = new EmployeesDaoImpl();
		ed.removeEmployee(emp_no);
	}
	@WebMethod
	public void updateEmployeeServ(@WebParam(name="emp_no") Integer emp_no, @WebParam(name="emp_no_nuovo")Integer emp_no_new, @WebParam(name="Data_nascita_nuova")String bd_new, @WebParam(name="nome_nuovo")String fn_new, @WebParam(name="cognome_nuovo")String ln_new, @WebParam(name="sesso")String g_new, @WebParam(name="data_assunzione") String hd_new) {
		EmployeesDao ed = new EmployeesDaoImpl();
		ed.updateEmployee(emp_no,emp_no_new,bd_new, fn_new, ln_new, g_new, hd_new);
	}
	@WebMethod
	@WebResult(name="EmployeesInfo",
    targetNamespace="http://simpleWeb2.it/userInfo")
	// @XmlJavaTypeAdapter(EmployeesAdapter.class)
	public List<Employees> readEmployeeServ(@WebParam(name="emp_no")Integer emp_no, @WebParam(name="data_nascita")String bd, @WebParam(name="nome")String fn, @WebParam(name="cognome")String ln, @WebParam(name="sesso")String g, @WebParam(name="data_assunzione")String hd) {
			EmployeesDao ed = new EmployeesDaoImpl();
			return ed.selectEmployee(emp_no, bd, fn, ln, g, hd);
	}
}
