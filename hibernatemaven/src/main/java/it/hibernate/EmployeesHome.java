package it.hibernate;

import com.mysql.jdbc.StringUtils;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Query;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;


















public class EmployeesHome
{
  private static final Logger log = Logger.getLogger(EmployeesHome.class);
  private static SessionFactory factory;
  
  public EmployeesHome() {
    try {
      factory = new Configuration().configure().buildSessionFactory();
    } catch (Throwable ex) {
      log.error("Failed to create sessionFactory object." + ex);
      throw new ExceptionInInitializerError(ex);
    }
  }
  
  public void insertEmployees(Employees o) { Session session = factory.openSession();
    Transaction tx = null;
    try {
      tx = session.beginTransaction();
      session.save(o);
      tx.commit();
    }
    catch (HibernateException e) {
      if (tx != null) tx.rollback();
      log.error(e);
    }
    finally {
      session.close();
    }
  }
  
  public void removeEmployee(Integer emp_no, Date birth_date, String first_name, String last_name, String g, Date hire_date) { 
	  Session session = factory.openSession();
	  ArrayList<Employees> delete = new ArrayList<Employees>();
	    Transaction tx = null;
	    try {
	      tx = session.beginTransaction();
	      StringBuilder s = new StringBuilder("FROM Employees e WHERE 1=1 ");
	      if (emp_no != null) {
	        s.append("AND e.empNo = :emp_no ");
	      }
	      if (birth_date != null) s.append("AND e.birthDate = :birth_date ");
	      if (first_name != null) s.append("AND e.firstName = :first_name ");
	      if (last_name != null) s.append("AND e.lastName = :last_name ");
	      if (g != null) s.append("AND e.gender = :gender ");
	      if(hire_date != null) s.append("AND e.hireDate = :hire_date");
	      Query q = session.createQuery(s.toString());
	      if (emp_no != null) {
	        q.setParameter("emp_no", emp_no);
	      }
	      if (birth_date != null) {
	        q.setParameter("birth_date", birth_date);
	      }
	      if (first_name != null) {
		        q.setParameter("first_name", first_name);
		      }
	      if (last_name != null) {
		        q.setParameter("last_name", last_name);
		      }
	      if (g != null) {
		        q.setParameter("gender", g);
		      }
	      if (hire_date != null) {
		        q.setParameter("hire_date", hire_date);
		      }
	      List result = q.getResultList();
	      for (Object o : result) {
	        delete.add((Employees)o);
	      }
	      for (Employees emp : delete) {
	        session.delete(emp);
	      }
	      tx.commit();
	    }
	    catch (HibernateException e) {
	      log.error(e);
	      return;
	    }
	    finally {
	      session.close();
	    }
  }
  
  public void updateEmployee(Integer emp_no, Date bd, String fn, String ln, String g, Date hd, Integer emp_no_new,Date bd_new, String fn_new, String ln_new, String g_new, Date hd_new) { 
	  if ((emp_no_new == null) && (bd_new == null) && (fn_new==null) && (ln_new==null) &&(g_new==null) && (hd_new==null)) return;
	  Session session = factory.openSession();
	  Transaction tx = null;
	   try {
	   	tx=session.beginTransaction();
	   	boolean emp_no_bool_new=true;
	   	boolean bd_bool_new=true;
	   	boolean fn_bool_new=true;
	   	boolean ln_bool_new=true;
	   	boolean g_bool_new=true;
	   	boolean hd_bool_new=true;
	   	if (emp_no_new == null) emp_no_bool_new = false;
	   	if(bd_new==null) bd_bool_new=false;
	   	if(StringUtils.isNullOrEmpty(fn_new)) fn_bool_new=false;
	   	if(StringUtils.isNullOrEmpty(ln_new)) ln_bool_new=false;
	   	if(StringUtils.isNullOrEmpty(g_new)) g_bool_new=false;
	   	if(hd_new==null) hd_bool_new=false;
		    StringBuilder hql = new StringBuilder("UPDATE Employees e");
		    if (emp_no_bool_new)
		      hql.append(" SET e.empNo = :emp_no_new");
		    if(bd_bool_new) {
		    	if(emp_no_bool_new)
		    		hql.append(" ,e.birthDate = :birth_date_new");
		    	else
		    		hql.append(" SET e.birthDate = :birth_date_new");
		    }
		    if(fn_bool_new) {
		    	if(emp_no_bool_new || bd_bool_new)
		    		hql.append(" ,e.firstName = :first_name_new");
		    	else
		    		hql.append(" SET e.firstName = :first_name_new");
		    }
		    if(ln_bool_new) {
		    	if(emp_no_bool_new || bd_bool_new || fn_bool_new)
		    		hql.append(" ,e.lastName = :last_name_new");
		    	else
		    		hql.append(" SET e.lastName = :last_name_new");
		    }
		    if(g_bool_new) {
		    	if(emp_no_bool_new || bd_bool_new || fn_bool_new || ln_bool_new)
		    		hql.append(" ,e.gender = :gender_new");
		    	else
		    		hql.append(" SET e.gender = :gender_new");
		    }
		    if(hd_bool_new) {
		    	if(emp_no_bool_new || bd_bool_new || fn_bool_new || ln_bool_new || g_bool_new)
		    		hql.append(" ,e.hireDate = :hire_date_new");
		    	else
		    		hql.append(" SET e.hireDate = :hire_date_new");
	   		}
		    hql.append(" WHERE 1=1");
		    boolean emp_no_bool=true;
		   	boolean bd_bool=true;
		   	boolean fn_bool=true;
		   	boolean ln_bool=true;
		   	boolean g_bool=true;
		   	boolean hd_bool=true;
		   	if (emp_no == null) emp_no_bool = false;
		   	if(bd==null) bd_bool=false;
		   	if(StringUtils.isNullOrEmpty(fn)) fn_bool=false;
		   	if(StringUtils.isNullOrEmpty(ln)) ln_bool=false;
		   	if(StringUtils.isNullOrEmpty(g)) g_bool=false;
		   	if(hd==null) hd_bool=false;
		    if (emp_no_bool)
		      hql.append(" AND e.empNo = :emp_no");
		    if(bd_bool) {
		    		hql.append(" AND e.birthDate = :birth_date");
		    }
		    if(fn_bool) {
		    	hql.append(" AND e.firstName = :first_name");
		    }
		    if(ln_bool) {
		    	hql.append(" AND e.lastName = :last_name");
		    }
		    if(g_bool) {
		    	hql.append(" AND e.gender = :gender");
		    }
		    if(hd_bool) {
		    	hql.append(" AND e.hireDate = :hire_date");
		    }
		    Query q = session.createQuery(hql.toString());
		    
		    if (emp_no_bool_new) q.setParameter("emp_no_new", emp_no_new);
		    if(bd_bool_new) q.setParameter("birth_date_new", bd_new);
		    if(fn_bool_new) q.setParameter("first_name_new",fn_new);
		    if(ln_bool_new) q.setParameter("last_name_new",ln_new);
		    if(g_bool_new) q.setParameter("gender_new", g_new);
		    if(hd_bool_new) q.setParameter("hire_date_new", hd_new);
		    
		    
		    if (emp_no_bool) q.setParameter("emp_no", emp_no);
		    if(bd_bool) q.setParameter("birth_date", bd);
		    if(fn_bool) q.setParameter("first_name",fn);
		    if(ln_bool) q.setParameter("last_name",ln);
		    if(g_bool) q.setParameter("gender", g);
		    if(hd_bool) q.setParameter("hire_date", hd);
		    int ret=q.executeUpdate();
		    log.info("Numero righe modificate: " + ret);
		    tx.commit();
	   }
	   catch (HibernateException e) {
	     log.error(e);
	     return;
	   }
	   finally {
	     session.close();
	   }
  }
  
  public int execUpdate(Query q, Integer emp_no, String bd, String fn, String ln, String g, String hd, int pk) {
    String query = "UPDATE Employees";
    if (emp_no != null) {
      q.setParameter("emp_no", emp_no);
    }
    if (bd != null) {
      q.setParameter("birth_date", bd);
    }
    if (fn != null) {
      q.setParameter("first_name", fn);
    }
    if (ln != null) {
      q.setParameter("last_name", ln);
    }
    if (g != null) {
      q.setParameter("gender", g);
    }
    if (hd != null) {
      q.setParameter("hire_date", hd);
    }
    q.setParameter("pk", Integer.valueOf(pk));
    return q.executeUpdate();
  }
  
  public static String constructString(Integer emp_no, String bd, String fn, String ln, String g, String hd) { 
	  String query = "UPDATE Employees";
    if (emp_no != null) {
      query = query + " SET empNo = :emp_no";
    }
    if (bd != null) {
      query = query + ", birthDate = :birth_date";
    }
    if (fn != null) {
      query = query + ", firstName = :first_name";
    }
    if (ln != null) {
      query = query + ", lastName = :last_name";
    }
    if (g != null) {
      query = query + ", gender = :gender";
    }
    if (hd != null) {
      query = query + ", hire_date = :hire_date";
    }
    return query += " WHERE emp_no = :pk";
  }
  
  public List<EmployeesOutput> readEmployees(Integer emp_no, Date birth_date, String first_name, String last_name, String gender, Date hire_date) { 
	  ArrayList<EmployeesOutput> result = new ArrayList<EmployeesOutput>();
    Session session = factory.openSession();
    Transaction tx = null;
    tx = session.beginTransaction();
    log.info("Select on employees");
    boolean emp_no_bool = true;
    if (emp_no == null) emp_no_bool = false;
    boolean bd_bool = birth_date != null;
    boolean fn_bool = !StringUtils.isNullOrEmpty(first_name);
    boolean ln_bool = !StringUtils.isNullOrEmpty(last_name);
    boolean g_bool = !StringUtils.isNullOrEmpty(gender);
    boolean hd_bool = hire_date != null;
    StringBuilder hql = new StringBuilder("FROM Employees e WHERE 1=1 ");
    if (emp_no_bool)
      hql.append("AND e.empNo = :emp_no");
    if (bd_bool)
      hql.append("AND e.birthDate = :birth_date");
    if (fn_bool)
      hql.append("AND e.firstName = :first_name");
    if (ln_bool)
      hql.append("AND e.lastName = :last_name");
    if (g_bool)
      hql.append("AND e.gender = :gender");
    if (hd_bool)
      hql.append("AND e.hireDate = :hire_date");
    Query q = session.createQuery(hql.toString());
    if (emp_no_bool) q.setParameter("emp_no", emp_no);
    if (bd_bool) q.setParameter("birth_date", birth_date);
    if (fn_bool) q.setParameter("first_name", first_name);
    if (ln_bool) q.setParameter("last_name", last_name);
    if (g_bool) q.setParameter("gender", gender);
    if (hd_bool) q.setParameter("hire_date", hire_date);
    List res = q.getResultList();
    for (Object o : res) {
      Employees e = (Employees)o;
      EmployeesOutput dout=new EmployeesOutput(e.getEmpNo(), e.getBirthDate(), e.getFirstName(), e.getLastName(), e.getGender(), e.getHireDate(), new HashSet<DeptEmpOutput>(), new HashSet<DeptManOutput>());
      Set<DeptEmp> dips=e.getDeptEmps();
      for(DeptEmp de : dips) {
    	  Departments d=de.getDepartments();
    	  SimpleDepartment sd = new SimpleDepartment(d.getDeptNo(), d.getDeptName());
    	  DeptEmpOutput dempout=new DeptEmpOutput(null,sd, de.getFromDate(), de.getToDate());
    	  dout.AddEmployeeDep(dempout);
      }
      Set<DeptManager> dipm=e.getDeptManagers();
      for(DeptManager dm : dipm) {
    	  Departments d=dm.getDepartments();
    	  SimpleDepartment sd = new SimpleDepartment(d.getDeptNo(), d.getDeptName());
    	  DeptManOutput deptmanout=new DeptManOutput(null,sd, dm.getFromDate(), dm.getToDate());
    	  dout.AddEmployeeMan(deptmanout);
      }
      result.add(dout);
    }
    return result;
  }
  public List<genderEmployeesOutput> genderEmployees(){
	  ArrayList<genderEmployeesOutput> result=new ArrayList<genderEmployeesOutput>();
	  Session session = factory.openSession();
	    Transaction tx = null;
	    tx = session.beginTransaction();
	    log.info("Getting employees for sex and title");
	    String hql = "SELECT t.id.title, e.gender, count(*) as count FROM Employees e INNER JOIN e.titleses t GROUP BY e.gender, t.id.title";
	    Query q=session.createQuery(hql);
	    List res=q.getResultList();
	    for(Object r: res){
	      Object[] row = (Object[]) r;
	      String title=(String) row[0];
	      String gender=(String) row[1];
	      long count=((long) row[2]);
	      genderEmployeesOutput geo=new genderEmployeesOutput(title, gender, count);
	      result.add(geo);
	    }
	    return result;
  }

public List<ManSalaryTitleOutput> ManSalaryTitleCount() {
	ArrayList<ManSalaryTitleOutput> result = new ArrayList<ManSalaryTitleOutput>();
	Session session = factory.openSession();
    Transaction tx = null;
    tx = session.beginTransaction();
    log.info("Getting manager count");
    String hql = "SELECT s.salary, t.id.title, count(*) FROM Titles t INNER JOIN t.employees e INNER JOIN e.deptManagers dm GROUP BY s.salary, t.id.title";
    Query q=session.createQuery(hql);
    List res=q.getResultList();
    for(Object r: res){
	      Object[] row = (Object[]) r;
	      int salary=(int) row[0];
	      String title=(String) row[1];
	      long count=(long) row[2];
	      ManSalaryTitleOutput msto=new ManSalaryTitleOutput(title,salary, count);
	      result.add(msto);
	    }
    return result;
}

public List<DepManInfoOutput> DepManEmployeeInfo(Integer emp_no) {
	ArrayList<DepManInfoOutput> result = new ArrayList<DepManInfoOutput>();
	Session session = factory.openSession();
    Transaction tx = null;
    tx = session.beginTransaction();
    log.info("Getting department managers info ");
    String hql = "SELECT d.deptNo, d.deptName, de.fromDate, de.toDate, em.empNo, em.birthDate, em.firstName, em.lastName, em.gender, em.hireDate, t.id.title, s.salary FROM Employees e INNER JOIN e.deptEmps de INNER JOIN de.departments d INNER JOIN d.deptManagers dm INNER JOIN dm.employees em INNER JOIN em.salarieses s INNER JOIN em.titleses t WHERE e.empNo= :emp_no AND ((dm.fromDate BETWEEN de.fromDate AND de.toDate) OR (dm.toDate BETWEEN de.fromDate AND de.toDate))";
    Query q=session.createQuery(hql);
    q.setParameter("emp_no", emp_no);
    List res=q.getResultList();
    for(Object r: res){
	      Object[] row = (Object[]) r;
	      String deptNo=(String) row[0];
	      String deptName=(String) row[1];
	      Date fromDate=(Date) row[2];
	      Date toDate=(Date) row[3];
	      int empNo=(int) row[4];
	      Date birthDate=(Date) row[5];
	      String fn=(String) row[6];
	      String ln=(String) row[7];
	      String g=(String) row[8];
	      Date hireDate=(Date) row[9];
	      String title=(String) row[10];
	      int salary=(int) row[11];
	      SimpleDepartment d=new SimpleDepartment(deptNo, deptName);
	      SimpleEmployee e=new SimpleEmployee(empNo, birthDate, fn, ln, g, hireDate);
	      DepManInfoOutput dmio=new DepManInfoOutput(e,d,fromDate,toDate,title,salary);
	      result.add(dmio);
	    }
    return result;
}

public List<RangeDistributionOutput> RangeDistributionInfo() {
	// TODO Auto-generated method stub
	ArrayList<RangeDistributionOutput> result = new ArrayList<RangeDistributionOutput>();
	Session session = factory.openSession();
    Transaction tx = null;
    tx = session.beginTransaction();
    log.info("Getting range distribution info ");
	int lowerbound=30000;
	int upperbound=50000;
	while(upperbound < 160000) {
		 String hql = "SELECT t.id.title, d.deptNo, d.deptName, count(*) FROM Titles t INNER JOIN t.employees e INNER JOIN e.salarieses s INNER JOIN e.deptEmps de INNER JOIN de.departments d WHERE s.salary >= :lowerbound AND s.salary <= :upperbound GROUP BY t.id.title, d.deptNo, d.deptName";
		 Query q=session.createQuery(hql);
		 q.setParameter("lowerbound", lowerbound);
		 q.setParameter("upperbound", upperbound);
		 List res=q.getResultList();
		    for(Object r: res){
			      Object[] row = (Object[]) r;
			      String title=(String) row[0];
			      String deptNo=(String) row[1];
			      String deptName=(String) row[2];
			      long count=(long) row[3];
			      RangeDistributionOutput rdo=new RangeDistributionOutput(title, deptNo, deptName, lowerbound, upperbound, count);
			      result.add(rdo);
			    }
		lowerbound+=20000;
		upperbound+=20000;
	}
	return result;
	}
}
