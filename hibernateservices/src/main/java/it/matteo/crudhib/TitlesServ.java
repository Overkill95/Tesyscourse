package it.matteo.crudhib;

import java.sql.Date;
import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

import org.apache.log4j.Logger;

import it.hibernate.Titles;
import it.hibernate.TitlesHome;
import it.hibernate.TitlesId;
@WebService(serviceName="ServiziTitoli", name="SimpleWebPortTypeTitle", targetNamespace="http://simpleWeb3.it")
public class TitlesServ {
	private static final Logger log=Logger.getLogger(DepartmentsServ.class);
	@WebMethod
	public void insertTitlesServ(@WebParam(name="emp_no") Integer emp_no , @WebParam(name="title")String title, @WebParam(name="fromdate") Date from_date, @WebParam(name = "todate") Date to_date) {
		    Titles t=new Titles();
			t.setToDate(to_date);
			TitlesId id=new TitlesId(emp_no,title, from_date);
			t.setId(id);
			TitlesHome th=new TitlesHome();
			th.insertTitles(t, emp_no);
	}
	@WebMethod
	public void deleteTitlesServ(@WebParam(name="emp_no")Integer emp_no, @WebParam(name="title")String t, @WebParam(name="fromdate") Date from_date, @WebParam(name="to_date") Date to_date) {
		TitlesHome eh = new TitlesHome();
		eh.removeTitle(emp_no, t, from_date, to_date);
	}
	@WebMethod
	public void updateTitlesServ(@WebParam(name="emp_no") Integer emp_no, @WebParam(name="title") String title, @WebParam(name="from_date") Date from_date, @WebParam(name="to_date") Date to_date, @WebParam(name="emp_no_new") Integer emp_no_new, @WebParam(name="title_new") String title_new, @WebParam(name="from_date_new") Date from_date_new, @WebParam(name="to_date_new")Date to_date_new ) {
		TitlesHome th = new TitlesHome();
		th.updateTitlesHome(emp_no, title, from_date, to_date, emp_no_new, title_new, from_date_new, to_date_new);
	}
	@WebMethod
	@WebResult(name="EmployeesInfo",
    targetNamespace="http://simpleWeb4.it/userInfo")
	// @XmlJavaTypeAdapter(EmployeesAdapter.class)
	public List<Titles> readTitlesServ(@WebParam(name="EmpNo")Integer emp_no, @WebParam(name="title")String title, @WebParam(name="from_date") Date from_date, @WebParam(name="to_date")Date to_date) {
			TitlesHome th = new TitlesHome();
			return th.readTitle(emp_no,title,from_date,to_date);
	}
}
