package it.matteo.dao;

import javax.jws.WebMethod;
import javax.jws.WebService;

import it.matteo.vo.DepartmentManager;
import it.matteo.vo.Departments;
//@WebService(name="WebServiceDepartmentManager", targetNamespace="http://www.provadepman.com")
public interface DepartmentManagerDao {
	//@WebMethod(operationName="InsertDepartmentManager")
	public void insertDepartmentManager(DepartmentManager o);
}
