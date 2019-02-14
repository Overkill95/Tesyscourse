package it.matteo.dao2;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebService;

import it.matteo.vo2.Employees;
//@WebService(name="WebServiceEmployees", targetNamespace="http://www.provaemp.com")
public interface EmployeesDao {
	//@WebMethod(operationName="InsertEmployees")
	public void insertEmployees(Employees o);
	public void removeEmployee(int emp_no);
	public void updateEmployee(int pk, Integer emp_no, String bd, String fn, String ln, String g, String hd);
	public List<Employees> selectEmployee(Integer emp_no, String bd, String fn, String ln, String g, String hd);
}
