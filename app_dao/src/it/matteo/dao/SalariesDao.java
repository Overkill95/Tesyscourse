package it.matteo.dao;

import javax.jws.WebMethod;
import javax.jws.WebService;

import it.matteo.vo.Employees;
import it.matteo.vo.Salaries;
//@WebService(name="WebServiceSalaries", targetNamespace="http://www.provasal.com")
public interface SalariesDao extends DaoInt {
	//@WebMethod(operationName="InsertSalaries")
	public void insertSalaries(Salaries o);
}
