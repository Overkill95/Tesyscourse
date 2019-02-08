package it.simple.app.services;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

import org.apache.log4j.Logger;

import it.simple.app.dao.SimpleDaoImpl;
import it.simple.app.dao.SimpleDaoInterface;
import it.simple.app.dto.UserInfo;

@WebService(serviceName="SimpleWebWs", name="SimpleWebPortType",
targetNamespace="http://simpleWeb.it")
public class SimpleWebAppWs {
	
	private static final Logger log=Logger.getLogger(SimpleWebAppWs.class);
	private static final SimpleDaoInterface dao=new SimpleDaoImpl();

	@WebMethod
	@WebResult(name="UserInfo",
      targetNamespace="http://simpleWeb.it/userInfo")
	public List<UserInfo> getUserInfo(
			@WebParam(name="name",
            targetNamespace="http://simpleWeb.it/name") String name) {
		log.info("getUserInfo execute");
		return dao.getUserInfo(name);
	}; 
	
}
