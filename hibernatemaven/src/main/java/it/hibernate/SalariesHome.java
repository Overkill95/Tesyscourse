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

/**
 * Home object for domain model class Salaries.
 * @see it.hibernate.Salaries
 * @author Hibernate Tools
 */
public class SalariesHome {

	private static final Logger log = Logger.getLogger(SalariesHome.class);
	  private static SessionFactory factory;
	  
	  public SalariesHome() {
	    try {
	      factory = new Configuration().configure().buildSessionFactory();
	    } catch (Throwable ex) {
	      log.error("Failed to create sessionFactory object." + ex);
	      throw new ExceptionInInitializerError(ex);
	    }
	  }
	  
	  public void insertSalaries(Salaries s, Integer emp_no, Integer salary, Date from_date, Date to_date) { 
		Session session = factory.openSession();
	    Transaction tx = null;
	    try {
	      tx = session.beginTransaction();
	      if (emp_no != null) {
	        Employees e = (Employees)session.load(Employees.class, emp_no);
	        e.AddSalarieses(s);
	        s.setEmployees(e);
	        s.getId().setEmpNo(emp_no);
	      }
	      session.save(s);
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
	  
	  public void removeSalary(Integer emp_no, Integer salary, Date fromDate, Date toDate) { 
		Session session = factory.openSession();
	    ArrayList<Salaries> delete = new ArrayList<Salaries>();
	    Transaction tx = null;
	    try {
	      tx = session.beginTransaction();
	      StringBuilder s = new StringBuilder("DELETE FROM Salaries s WHERE 1=1 ");
	      if (emp_no != null) {
	        s.append("AND s.employees.empNo = :emp_no ");
	      }
	      if (salary != null) s.append("AND s.salary = :salary");
	      if (fromDate != null) s.append("AND s.id.fromDate = :from_date");
	      if (toDate != null) s.append("AND s.toDate = :to_date");
	      Query q = session.createQuery(s.toString());
	      if(emp_no!=null)
	    	  q.setParameter("emp_no", emp_no);
	      if(salary!=null)
	    	  q.setParameter("salary", salary);
	      if (fromDate != null) {
	        q.setParameter("from_date", fromDate);
	      }
	      if (toDate != null) {
	        q.setParameter("to_date", toDate);
	      }
	      List result = q.getResultList();
	      for (Object o : result) {
	        delete.add((Salaries)o);
	      }
	      for (Salaries d : delete) {
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
	  
	  public void updateSalariesHome(Integer emp_no, Date fromDate, Integer salary, Date toDate, Integer emp_no_new, Integer salary_new, Date from_date_new, Date to_date_new) { 
		   Session session = factory.openSession();
		   Transaction tx = null;
		    try {
		    	tx=session.beginTransaction();
		    	boolean emp_no_bool_new=true;
		    	if (emp_no_new == null) emp_no_bool_new = false;
			    boolean s_bool_new = salary_new!=null;
			    boolean fd_bool_new = from_date_new!=null;
			    boolean td_bool_new = to_date_new!=null;
			    if(emp_no_new ==null && salary_new == null && from_date_new == null && to_date_new ==null) return;
			    StringBuilder hql = new StringBuilder("UPDATE Salaries s ");
			    if (emp_no_bool_new)
			      hql.append("SET s.id.empNo = :emp_no_new ");
			    if(s_bool_new) {
			    	if(emp_no_bool_new)
			    		hql.append(" ,s.salary = :salary_new ");
			    	else
			    		hql.append(" SET s.salary = :salary_new ");
			    }
			    if(fd_bool_new) {
			    	if(emp_no_bool_new || s_bool_new)
			    		hql.append(" ,s.fromDate = :from_date_new");
			    	else
			    		hql.append(" SET s.fromDate = :from_date_new");
			    }
			    if(td_bool_new) {
			    	if(emp_no_bool_new || s_bool_new || fd_bool_new)
			    		hql.append(" ,s.toDate = :to_date_new");
			    	else
			    		hql.append(" SET s.toDate = :to_date_new");
			    }
			    
			    
			    hql.append(" WHERE 1=1");
			    boolean emp_no_bool=true;
		    	if (emp_no == null) emp_no_bool = false;
			    boolean s_bool = salary!=null;
			    boolean fd_bool = fromDate!=null;
			    boolean td_bool = toDate!=null;
			    if (emp_no_bool)
			      hql.append(" AND s.id.empNo = :emp_no");
			    if(s_bool) {
			    		hql.append(" AND s.salary = :salary ");
			    }
			    if(fd_bool) {
			    		hql.append(" AND s.fromDate = :from_date");
			    }
			    if(td_bool) {
			    		hql.append(" AND s.toDate = :to_date");
			    }
			    
			    Query q = session.createQuery(hql.toString());
			    
			    if (emp_no_bool_new) q.setParameter("emp_no_new", emp_no_new);
			    if(s_bool_new) q.setParameter("salary_new", salary_new);
			    if (fd_bool_new) q.setParameter("from_date_new", from_date_new);
			    if (td_bool_new)q.setParameter("to_date_new", to_date_new);
			    
			    if (emp_no_bool) q.setParameter("emp_no", emp_no);
			    if(s_bool) q.setParameter("title", salary);
			    if (fd_bool) q.setParameter("from_date", fromDate);
			    if (td_bool)q.setParameter("to_date", toDate);
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
	  
	  public List<Salaries> readSalary( Integer emp_no, Integer salary, Date from_date, Date to_date) { 
	    ArrayList<Salaries> result = new ArrayList<Salaries>();
	    Session session = factory.openSession();
	    Transaction tx = null;
	    tx = session.beginTransaction();
	    log.info("Select on salaries");
	    boolean emp_no_bool = true;
	    if (emp_no == null) emp_no_bool = false;
	    boolean s_bool = salary!=null;
	    boolean fd_bool = from_date!=null;
	    boolean td_bool = to_date!=null;
	    StringBuilder hql = new StringBuilder("FROM Salaries s WHERE 1=1 ");
	    if (emp_no_bool)
	      hql.append("AND s.employees.empNo = :emp_no ");
	    if(s_bool)
	    	hql.append("AND s.salary = :salary ");
	    if (fd_bool)
	      hql.append("AND s.from_date = :from_date ");
	    if (td_bool)
	      hql.append("AND s.toDate = :to_date");
	    Query q = session.createQuery(hql.toString());
	    if (emp_no_bool) q.setParameter("emp_no", emp_no);
	    if (s_bool) q.setParameter("salary", salary);
	    if (fd_bool) q.setParameter("from_date", from_date);
	    if (td_bool)q.setParameter("to_date", to_date);
	    q.setMaxResults(20);
	    List res = q.getResultList();
	    for (Object o : res) {
	      Salaries d = (Salaries) o;
	      result.add(d);
	    }
	    return result;
	  }

	public List<MaxMinSalaryOutput> MaxMinSalary() {
		// TODO Auto-generated method stub
		ArrayList<MaxMinSalaryOutput> result = new ArrayList<MaxMinSalaryOutput>();
		Session session = factory.openSession();
	    Transaction tx = null;
	    tx = session.beginTransaction();
	    log.info("Getting max min salary");
	    String hql = "SELECT t.id.title, d.deptName, max(s.salary) as max, min(s.salary) as min FROM Employees e INNER JOIN e.titleses t INNER JOIN e.deptEmps de INNER JOIN de.departments d INNER JOIN e.salarieses s GROUP BY t.id.title, d.deptName";
	    Query q=session.createQuery(hql);
	    q.setMaxResults(20);
	    List res=q.getResultList();
	    for(Object r: res){
		      Object[] row = (Object[]) r;
		      String title=(String) row[0];
		      String deptName=(String) row[1];
		      int max=((int) row[2]);
		      int min=((int) row[3]);
		      MaxMinSalaryOutput mso=new MaxMinSalaryOutput(deptName, title, max, min);
		      result.add(mso);
		    }
		return result;
	}
	public List<SalaryCountOutput> SalaryCount() {
		// TODO Auto-generated method stub
		ArrayList<SalaryCountOutput> result = new ArrayList<SalaryCountOutput>();
		Session session = factory.openSession();
	    Transaction tx = null;
	    tx = session.beginTransaction();
	    log.info("Getting salary count");
	    String hql = "SELECT s.salary, count(*) FROM Employees e INNER JOIN e.salarieses s GROUP BY s.salary";
	    Query q=session.createQuery(hql);
	    q.setMaxResults(20);
	    List res=q.getResultList();
	    for(Object r: res){
		      Object[] row = (Object[]) r;
		      int salary=(int) row[0];
		      long count=(long) row[1];
		      SalaryCountOutput sco=new SalaryCountOutput(salary,count);
		      result.add(sco);
		    }
	    return result;
	}
}
