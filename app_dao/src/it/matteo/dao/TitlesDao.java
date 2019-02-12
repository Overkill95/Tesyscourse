package it.matteo.dao;

import javax.jws.WebMethod;
import javax.jws.WebService;

import it.matteo.vo.Employees;
import it.matteo.vo.Titles;
//@WebService(name="WebServiceTitles", targetNamespace="http://www.provatitl.com")
public interface TitlesDao {
	//@WebMethod(operationName="InsertTitles")
	public void insertTitles(Titles t);
}
