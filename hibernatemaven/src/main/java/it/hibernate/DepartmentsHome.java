package it.hibernate;

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

import com.mysql.jdbc.StringUtils;

import it.matteo.hibernatemaven.utils.InvalidInputException;





















public class DepartmentsHome
{
  private static final Logger log = Logger.getLogger(DepartmentsHome.class);
  private static SessionFactory factory;
  
  public DepartmentsHome() {
    try {
      factory = new Configuration().configure().buildSessionFactory();
    } catch (Throwable ex) {
      log.error("Failed to create sessionFactory object." + ex);
      throw new ExceptionInInitializerError(ex);
    }
  }
  
  public void insertDepartment(Departments d) { Session session = factory.openSession();
    Transaction tx = null;
    try {
      tx = session.beginTransaction();
      session.save(d);
      log.info("Saved department with: " + "dept_no:"+ d.getDeptNo()+ " Name: " + d.getDeptName());
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
  
  public void removeDepartment(String dept_no, String deptName) { Session session = factory.openSession();
    ArrayList<Departments> delete = new ArrayList();
    Transaction tx = null;
    try {
      tx = session.beginTransaction();
      StringBuilder s = new StringBuilder("FROM Departments d WHERE 1=1");
      if (dept_no != null) {
        s.append(" AND d.deptNo = :dept_no ");
      }
      if (deptName != null) s.append(" AND d.deptName = :dept_name");
      Query q = session.createQuery(s.toString());
      if (dept_no != null) {
        q.setParameter("dept_no", dept_no);
      }
      if (deptName != null) {
        q.setParameter("dept_name", deptName);
      }
      List result = q.getResultList();
      for (Object o : result) {
    	Departments d=(Departments) o;
    	log.info("Deleting department:" + d.getDeptNo() + " With name: " + d.getDeptName());
        delete.add(d);
      }
      for (Departments d : delete) {
        session.delete(d);
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
  
  public void updateDepartments(String dept_no, String dept_name, String dept_no_new, String dept_name_new) throws InvalidInputException { 
	  if ((dept_no_new == null) && (dept_name_new == null)) return;
  Session session = factory.openSession();
  Transaction tx = null;
   try {
   	tx=session.beginTransaction();
   	boolean dept_no_bool_new=true;
   	if (dept_no_new == null) dept_no_bool_new = false;
	    boolean dept_bool_new = !StringUtils.isNullOrEmpty(dept_name_new);
	    StringBuilder hql = new StringBuilder("UPDATE Departments d");
	    if (dept_no_bool_new)
	      hql.append(" SET d.deptNo = :dept_no_new");
	    if(dept_bool_new) {
	    	if(dept_no_bool_new)
	    		hql.append(" ,d.deptName = :dept_name_new");
	    	else
	    		hql.append(" SET d.deptName = :dept_name_new");
	    }
	    
	    
	    hql.append(" WHERE 1=1");
	    boolean dept_no_bool=true;
   	if (dept_no == null) dept_no_bool = false;
	    boolean dept_bool = !StringUtils.isNullOrEmpty(dept_name);
	    if (dept_no_bool)
	      hql.append(" AND d.deptNo = :dept_no");
	    if(dept_bool) {
	    		hql.append(" AND d.deptName = :dept_name ");
	    }
	    Query q = session.createQuery(hql.toString());
	    
	    if (dept_no_bool_new) q.setParameter("dept_no_new", dept_no_new);
	    if(dept_bool_new) q.setParameter("dept_name_new", dept_name_new);
	    
	    if (dept_no_bool) q.setParameter("dept_no", dept_no);
	    if(dept_bool) q.setParameter("dept_name", dept_name);
	    log.info("Query invocata: " + hql.toString());
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
  
  public int execUpdate(Query q, String dept_no, Integer emp_no, String pkemp, String pkdep) { String query = "UPDATE Employees";
    if (emp_no != null) {
      q.setParameter("emp_no", emp_no);
    }
    if (dept_no != null) {
      q.setParameter("dept_no", dept_no);
    }
    q.setParameter("pkemp", pkemp);
    q.setParameter("pkdep", pkdep);
    return q.executeUpdate();
  }
  
  public static String constructString(Integer emp_no, String dept_no) { String query = "UPDATE Departments";
    if (emp_no != null) {
      query = query + " SET emp_no = :emp_no";
    }
    if (dept_no != null) {
      query = query + ", dept_no = :dept_no";
    }
    return query += " WHERE emp_no = :pkemp AND dept_no = :pkdep";
  }
  
  public List<DepartmentsOutput> readDepartments(String deptNo, String deptName) { 
	ArrayList<DepartmentsOutput> result = new ArrayList<DepartmentsOutput>();
    Session session = factory.openSession();
    Transaction tx = null;
    tx = session.beginTransaction();
    log.info("Select on departments");
    boolean dept_no_bool = !StringUtils.isNullOrEmpty(deptNo);
    boolean deptname_bool = !StringUtils.isNullOrEmpty(deptName);
    StringBuilder hql = new StringBuilder("FROM Departments d WHERE 1=1");
    if (dept_no_bool)
      hql.append(" AND d.deptNo = :dept_no");
    if (deptname_bool)
      hql.append(" AND d.deptName = :dept_name");
    Query q = session.createQuery(hql.toString());
    if (dept_no_bool) q.setParameter("dept_no", deptNo);
    if (deptname_bool) q.setParameter("dept_name", deptName);
    q.setMaxResults(20);
    log.info("Query invocata: " + hql.toString());
    List res = q.getResultList();
    for (Object o : res) {
      Departments d = (Departments)o;
      DepartmentsOutput dout=new DepartmentsOutput(d.getDeptNo(), d.getDeptName(), new HashSet<DeptEmpOutput>(), new HashSet<DeptManOutput>());
      Set<DeptEmp> dips=d.getDeptEmps();
      for(DeptEmp de : dips) {
    	  Employees e=de.getEmployees();
    	  SimpleEmployee se = new SimpleEmployee(e.getEmpNo(), e.getBirthDate(), e.getFirstName(), e.getLastName(), e.getGender(), e.getHireDate());
    	  DeptEmpOutput dempout=new DeptEmpOutput(se,null, de.getFromDate(), de.getToDate());
    	  dout.AddEmployeeDep(dempout);
      }
      Set<DeptManager> dipm=d.getDeptManagers();
      for(DeptManager dm : dipm) {
    	  Employees e=dm.getEmployees();
    	  SimpleEmployee se = new SimpleEmployee(e.getEmpNo(), e.getBirthDate(), e.getFirstName(), e.getLastName(), e.getGender(), e.getHireDate());
    	  DeptManOutput deptmanout=new DeptManOutput(se,null, dm.getFromDate(), dm.getToDate());
    	  dout.AddEmployeeMan(deptmanout);
      }
      result.add(dout);
    }
    return result;
  }
  
  public void insertEmployeeToDepart(String dept_no, Integer emp_id, Date from_date, Date to_date) throws InvalidInputException {
    if (dept_no == null) throw new InvalidInputException("Non è stato specificato nessun dipartimento");
    if (emp_id == null) throw new InvalidInputException("id impiegato non inserito");
    Session session = factory.openSession();
    Transaction tx = null;
    try {
      tx = session.beginTransaction();
      DepartmentsHome dh = new DepartmentsHome();
      Employees e = (Employees)session.load(Employees.class, emp_id);
      Departments d = (Departments)session.load(Departments.class, dept_no);
      DeptEmp de = new DeptEmp();
      de.setDepartments(d);
      de.setEmployees(e);
      de.setFromDate(from_date);
      de.setToDate(to_date);
      d.AddDeptEmps(de);
      e.AddDeptEmps(de);
      session.save(de);
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

public List<DepCountOutput> DepCount() {
	// TODO Auto-generated method stub
	ArrayList<DepCountOutput> result = new ArrayList<DepCountOutput>();
	Session session = factory.openSession();
    Transaction tx = null;
    tx = session.beginTransaction();
    log.info("Getting dep count");
    String hql = "SELECT d.deptNo, d.deptName, count(*) FROM Departments d INNER JOIN d.deptEmps de join de.employees e GROUP BY d.deptNo";
    Query q=session.createQuery(hql);
    q.setMaxResults(20);
    log.info("Query invocata: " + hql.toString());
    List res=q.getResultList();
    for(Object r: res){
	      Object[] row = (Object[]) r;
	      String deptNo=(String) row[0];
	      String deptName=(String) row[1];
	      long count=(long) row[2];
	      SimpleDepartment sd=new SimpleDepartment(deptNo, deptName);
	      DepCountOutput dco=new DepCountOutput(sd,count);
	      result.add(dco);
	    }
    return result;
	}

public List<ManCountOutput> ManCount() {
	// TODO Auto-generated method stub
		ArrayList<ManCountOutput> result = new ArrayList<ManCountOutput>();
		Session session = factory.openSession();
	    Transaction tx = null;
	    tx = session.beginTransaction();
	    log.info("Getting manager count");
	    String hql = "SELECT d.deptNo, d.deptName, count(*) FROM Departments d INNER JOIN d.deptMans dm join dm.employees e GROUP BY d.deptNo";
	    Query q=session.createQuery(hql);
	    q.setMaxResults(20);
	    log.info("Query invocata: " + hql.toString());
	    List res=q.getResultList();
	    for(Object r: res){
		      Object[] row = (Object[]) r;
		      String deptNo=(String) row[0];
		      String deptName=(String) row[1];
		      long count=(long) row[2];
		      SimpleDepartment sd=new SimpleDepartment(deptNo, deptName);
		      ManCountOutput mco=new ManCountOutput(sd,count);
		      result.add(mco);
		    }
	    return result;
}
}
