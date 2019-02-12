package it.matteo.crud;
import java.util.*;

import javax.jws.*;
import org.apache.log4j.Logger;

import it.matteo.dao.Dao;
import it.matteo.dao.EmployeesDao;
import it.matteo.dao.EmployeesDaoImpl;
import it.matteo.utils.InvalidInputException;
import it.matteo.vo.Employees;
import it.matteo.vo.EmployeesAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
@WebService(serviceName="ServiziImpiegati", name="SimpleWebPortType2",
targetNamespace="http://simpleWeb2.it")
public class CreateEmployeesServ {
	private static final Logger log=Logger.getLogger(CreateEmployeesServ.class);
	@WebMethod
	public void insertEmployeeServ(int emp_no, String bd, String fn, String ln, String g, String hd) {
		Employees e;
		try {
			e = new Employees(emp_no, bd, fn, ln, g, hd);
			EmployeesDao ed=new EmployeesDaoImpl();
			ed.insertEmployees(e);
		} catch (InvalidInputException e1) {
			// TODO Auto-generated catch block
			log.error(e1);
			return;
		}
	}
	@WebMethod
	public void deleteEmployeeServ(Integer emp_no) {
		EmployeesDao ed = new EmployeesDaoImpl();
		ed.removeEmployee(emp_no);
	}
	@WebMethod
	public void updateEmployeeServ(Integer emp_no, Integer emp_no_new, String bd_new, String fn_new, String ln_new, String g_new, String hd_new) {
		EmployeesDao ed = new EmployeesDaoImpl();
		ed.updateEmployee(emp_no,emp_no_new,bd_new, fn_new, ln_new, g_new, hd_new);
	}
	@WebMethod
	@WebResult(name="EmployeesInfo",
    targetNamespace="http://simpleWeb2.it/userInfo")
	@XmlJavaTypeAdapter(EmployeesAdapter.class)
	public List<Employees> readEmployeeServ(Integer emp_no, String bd, String fn, String ln, String g, String hd) {
			EmployeesDao ed = new EmployeesDaoImpl();
			return ed.selectEmployee(emp_no, bd, fn, ln, g, hd);
	}
}
