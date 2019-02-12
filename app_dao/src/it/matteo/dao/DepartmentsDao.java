package it.matteo.dao;
import java.util.*;

import javax.jws.WebMethod;
import javax.jws.WebService;

import it.matteo.vo.Departments;
//@WebService(name="WebServiceDepartment", targetNamespace="http://www.provadep.com")
public interface DepartmentsDao {
	public List<Departments> getDepartments(String dept_no, String dept_name);
	//@WebMethod(operationName="InsertDepartment")
	public void insertDepartments(Departments o);
}
