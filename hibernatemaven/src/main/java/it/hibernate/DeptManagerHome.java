package it.hibernate;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.mysql.jdbc.StringUtils;
















public class DeptManagerHome
{
  private static final Logger log = Logger.getLogger(DeptManagerHome.class);
  private static SessionFactory factory;
  
  public DeptManagerHome() {
    try {
      factory = new Configuration().configure().buildSessionFactory();
    } catch (Throwable ex) {
      log.error("Failed to create sessionFactory object." + ex);
      throw new ExceptionInInitializerError(ex);
    }
  }
  
  public void insertDeptMan(DeptManager dp, String dept_no, Integer emp_no) { Session session = factory.openSession();
    Transaction tx = null;
    try {
      tx = session.beginTransaction();
      if (dept_no != null) {
        Departments d = (Departments)session.load(Departments.class, dept_no);
        d.AddDeptManagers(dp);
        dp.setDepartments(d);
      }
      if (emp_no != null) {
        Employees e = (Employees)session.load(Employees.class, emp_no);
        e.AddDeptManagers(dp);
        session.flush();
        dp.setEmployees(e);
      }
      session.save(dp);
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
  
  public void removeDeptManager(String dept_no, Integer emp_no) { Session session = factory.openSession();
    ArrayList<DeptManager> delete = new ArrayList<DeptManager>();
    Transaction tx = null;
    try {
      tx = session.beginTransaction();
      StringBuilder s = new StringBuilder("DELETE FROM DeptManager d WHERE 1=1 ");
      if (dept_no != null) {
        s.append("AND d.departments.deptNo = :dept_no ");
      }
      if (emp_no != null) s.append("AND d.employees.empNo = :emp_no");
      Query q = session.createQuery(s.toString());
      if (dept_no != null) {
        q.setParameter("dept_no", dept_no);
      }
      if (emp_no != null) {
        q.setParameter("emp_no", emp_no);
      }
      List result = q.getResultList();
      for (Object o : result) {
        delete.add((DeptManager)o);
      }
      for (DeptManager d : delete) {
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
  
  public void updateDeptManHome(String dept_no, Integer emp_no, Date from_date, Date to_date, String dept_no_new, Integer emp_no_new, Date from_date_new, Date to_date_new) { 
	  if ((dept_no_new == null) && (emp_no_new == null) && (from_date_new==null) && (to_date_new==null)) return;
	  Session session = factory.openSession();
	  Transaction tx = null;
	   try {
	   	    tx=session.beginTransaction();
	   	    boolean dept_no_bool_new=true;
	   	    boolean emp_no_bool_new=true;
	   	    boolean fd_bool_new=true;
	   	    boolean td_bool_new=true;
	   	    if (dept_no_new == null) dept_no_bool_new = false;
		    if(emp_no_new==null) emp_no_bool_new=false;
		    if(from_date_new==null) fd_bool_new=false;
		    if(to_date_new==null) td_bool_new=false;
		    StringBuilder hql = new StringBuilder("UPDATE DeptEmp d");
		    if (dept_no_bool_new)
		      hql.append(" SET d.id.deptNo = :dept_no_new");
		    if(emp_no_bool_new) {
		    	if(dept_no_bool_new)
		    		hql.append(" ,d.id.empNo = :emp_no_new");
		    	else
		    		hql.append(" SET d.id.empNo = :emp_no_new");
		    }
		    if(fd_bool_new) {
		    	if(emp_no_bool_new || dept_no_bool_new)
		    		hql.append(" ,d.fromDate = :from_date_new");
		    	else
		    		hql.append(" SET d.fromDate = :from_date_new");
		    }
		    if(td_bool_new) {
		    	if(emp_no_bool_new || dept_no_bool_new || fd_bool_new)
		    		hql.append(" ,d.toDate = :to_date_new");
		    	else
		    		hql.append(" SET d.toDate = :to_date_new");
		    }
		    hql.append(" WHERE 1=1");
		    boolean dept_no_bool=true;
		    boolean emp_no_bool=true;
		    boolean fd_bool=true;
		    boolean td_bool=true;
		    if (dept_no == null) dept_no_bool = false;
		    if(emp_no == null) emp_no_bool = false;
		    if(from_date == null) fd_bool=false;
		    if(to_date == null) td_bool=false;
		    if (dept_no_bool)
		      hql.append(" AND d.id.deptNo = :dept_no");
		    if(emp_no_bool) {
		       hql.append(" AND d.id.empNo = :emp_no");
		    }
		    if(fd_bool)
		       hql.append(" AND d.fromDate = :from_date");
		    if(td_bool)
			   hql.append(" AND d.toDate = :to_date");
		    Query q = session.createQuery(hql.toString());
		    
		    if (dept_no_bool_new) q.setParameter("dept_no_new", dept_no_new);
		    if(emp_no_bool_new) q.setParameter("emp_no_new", emp_no_new);
		    if(fd_bool_new) q.setParameter("from_date_new",from_date_new);
		    if(td_bool_new) q.setParameter("to_date_new", to_date_new);
		    if(dept_no_bool) q.setParameter("dept_no", dept_no);
		    if(emp_no_bool) q.setParameter("emp_no", emp_no);
		    if(fd_bool) q.setParameter("from_date", from_date);
		    if(td_bool) q.setParameter("to_date", to_date);
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
  
  public List<DeptManager> readDeptManager(String deptNo, Integer emp_no, String from_date, String to_date) { 
	ArrayList<DeptManager> result = new ArrayList<DeptManager>();
    Session session = factory.openSession();
    Transaction tx = null;
    tx = session.beginTransaction();
    log.info("Select on departments");
    boolean emp_no_bool = true;
    if (emp_no == null) emp_no_bool = false;
    boolean dept_no_bool = !StringUtils.isNullOrEmpty(deptNo);
    boolean fd_bool = !StringUtils.isNullOrEmpty(from_date);
    boolean td_bool = !StringUtils.isNullOrEmpty(to_date);
    StringBuilder hql = new StringBuilder("FROM DeptManager d WHERE 1=1 ");
    if (dept_no_bool)
      hql.append("AND d.departments.deptNo = :dept_no ");
    if (emp_no_bool)
      hql.append("AND d.employees.empNo = :emp_no ");
    if (fd_bool)
      hql.append("AND d.fromDate = :from_date ");
    if (td_bool)
      hql.append("AND d.toDate = :to_date");
    Query q = session.createQuery(hql.toString());
    if (dept_no_bool) q.setParameter("dept_no", deptNo);
    if (emp_no_bool) q.setParameter("emp_no", emp_no);
    if (fd_bool) q.setParameter("from_date", from_date);
    if (td_bool)q.setParameter("to_date", to_date);
    List res = q.getResultList();
    for (Object o : res) {
      DeptManager d = (DeptManager)o;
      result.add(d);
    }
    return result;
  }
}