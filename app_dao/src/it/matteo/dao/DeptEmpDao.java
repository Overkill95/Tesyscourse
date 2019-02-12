package it.matteo.dao;

import javax.jws.WebMethod;
import javax.jws.WebService;

import it.matteo.vo.DeptEmp;
//@WebService(name="WebServiceDeptEmp", targetNamespace="http://www.provadepemp.com")
public interface DeptEmpDao{
	//@WebMethod(operationName="InsertDeptEmp")
	public void insertDeptEmp(DeptEmp o);
}
