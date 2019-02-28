package it.hibernate;
// Generated 22-feb-2019 8.27.09 by Hibernate Tools 5.0.6.Final

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;
import javax.sound.midi.MidiDevice.Info;

//import org.apache.commons.logging.Log;
//import org.apache.commons.logging.LogFactory;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.mysql.jdbc.StringUtils;

/**
 * Home object for domain model class Titles.
 * @see it.hibernate.Titles
 * @author Hibernate Tools
 */
//@Stateless
public class TitlesHome {

	private static final Logger log = Logger.getLogger(DeptEmpHome.class);
	  private static SessionFactory factory;
	  private static Session session;
	  public TitlesHome() {
	    try {
	      factory = new Configuration().configure().buildSessionFactory();
	    } catch (Throwable ex) {
	      log.error("Failed to create sessionFactory object." + ex);
	      throw new ExceptionInInitializerError(ex);
	    }
	  }
	  
	  public void insertTitles(Titles t, Integer emp_no) { 
		session = factory.openSession();
	    Transaction tx = null;
	    try {
	      tx = session.beginTransaction();
	      if (emp_no != null) {
	        Employees e = (Employees)session.load(Employees.class, emp_no);
	        e.AddTitleses(t);
	        t.getId().setEmpNo(emp_no);
	        t.setEmployees(e);
	      }
	      session.save(t);
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
	  
	  public void removeTitle(Integer emp_no, String title, Date fromDate, Date toDate) { 
		session = factory.openSession();
	    ArrayList<Titles> delete = new ArrayList<Titles>();
	    Transaction tx = null;
	    try {
	      tx = session.beginTransaction();
	      StringBuilder s = new StringBuilder("FROM Titles t WHERE 1=1");
	      if (emp_no != null) {
	        s.append(" AND t.id.empNo = :emp_no ");
	      }
	      if (title != null) s.append(" AND t.id.title = :title");
	      if (fromDate != null) s.append(" AND t.id.fromDate = :from_date");
	      if (toDate != null) s.append(" AND t.toDate = :to_date");
	      Query q = session.createQuery(s.toString());
	      if(emp_no!=null)
	    	  q.setParameter("emp_no", emp_no);
	      if(title!=null)
	    	  q.setParameter("title", title);
	      if (fromDate != null) {
	        q.setParameter("from_date", fromDate);
	      }
	      if (toDate != null) {
	        q.setParameter("to_date", toDate);
	      }
	      List result = q.getResultList();
	      for (Object o : result) {
	        delete.add((Titles)o);
	      }
	      for (Titles d : delete) {
	    	log.info(" Deleting title: "+d.getId().getTitle() +" For Employee: " + d.getEmployees().getEmpNo()+ " from Date: " + d.getId().getFromDate() + " To Date: " + d.getToDate());
	        session.delete(d);
	        log.info("Delete success");
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
	  
	  public void updateTitlesHome(Integer emp_no, String title, Date fromDate, Date toDate, Integer emp_no_new, String title_new, Date from_date_new, Date to_date_new) { 
		session = factory.openSession();
	    Transaction tx = null;
	    try {
	    	tx=session.beginTransaction();
	    	boolean emp_no_bool_new=true;
	    	if (emp_no_new == null) emp_no_bool_new = false;
		    boolean t_bool_new = !StringUtils.isNullOrEmpty(title_new);
		    boolean fd_bool_new = from_date_new!=null;
		    boolean td_bool_new = to_date_new!=null;
		    if(emp_no_new ==null && title_new == null && from_date_new == null && to_date_new ==null) return;
		    StringBuilder hql = new StringBuilder("UPDATE Titles t ");
		    if (emp_no_bool_new)
		      hql.append("SET t.id.empNo = :emp_no_new");
		    if(t_bool_new) {
		    	if(emp_no_bool_new)
		    		hql.append(" ,t.id.title = :title_new");
		    	else
		    		hql.append(" SET t.id.title = :title_new");
		    }
		    if(fd_bool_new) {
		    	if(emp_no_bool_new || t_bool_new)
		    		hql.append(" ,t.fromDate = :from_date_new");
		    	else
		    		hql.append(" SET t.fromDate = :from_date_new");
		    }
		    if(td_bool_new) {
		    	if(emp_no_bool_new || t_bool_new || fd_bool_new)
		    		hql.append(" ,t.toDate = :to_date_new");
		    	else
		    		hql.append(" SET t.toDate = :to_date_new");
		    }
		    
		    
		    hql.append(" WHERE 1=1");
		    boolean emp_no_bool=true;
	    	if (emp_no == null) emp_no_bool = false;
		    boolean t_bool = !StringUtils.isNullOrEmpty(title);
		    boolean fd_bool = fromDate!=null;
		    boolean td_bool = toDate!=null;
		    if (emp_no_bool)
		      hql.append(" AND t.id.empNo = :emp_no");
		    if(t_bool) {
		    		hql.append(" AND t.id.title = :title ");
		    }
		    if(fd_bool) {
		    		hql.append(" AND t.fromDate = :from_date");
		    }
		    if(td_bool) {
		    		hql.append(" AND t.toDate = :to_date");
		    }
	
		    Query q = session.createQuery(hql.toString());
		    
		    if (emp_no_bool_new) q.setParameter("emp_no_new", emp_no_new);
		    if(t_bool_new) q.setParameter("title_new", title_new);
		    if (fd_bool_new) q.setParameter("from_date_new", from_date_new);
		    if (td_bool_new)q.setParameter("to_date_new", to_date_new);
		    
		    if (emp_no_bool) q.setParameter("emp_no", emp_no);
		    if(t_bool) q.setParameter("title", title);
		    if (fd_bool) q.setParameter("from_date", fromDate);
		    if (td_bool)q.setParameter("to_date", toDate);
		    log.info("Query invocata:" + hql.toString());
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
	  
	  public List<TitlesOutput> readTitle( Integer emp_no, String title, Date from_date, Date to_date) { 
	    ArrayList<TitlesOutput> result = new ArrayList<TitlesOutput>();
	     session = factory.openSession();
	    log.info("Select on titles");
	    boolean emp_no_bool = true;
	    if (emp_no == null) emp_no_bool = false;
	    boolean t_bool = !StringUtils.isNullOrEmpty(title);
	    boolean fd_bool = from_date!=null;
	    boolean td_bool = to_date!=null;
	    StringBuilder hql = new StringBuilder("FROM Titles t WHERE 1=1");
	    if (emp_no_bool)
	      hql.append(" AND t.employees.empNo = :emp_no");
	    if(t_bool)
	    	hql.append(" AND t.id.title = :title");
	    if (fd_bool)
	      hql.append(" AND t.id.fromDate = :from_date");
	    if (td_bool)
	      hql.append(" AND t.toDate = :to_date");
	    Query q = session.createQuery(hql.toString());
	    if (emp_no_bool) q.setParameter("emp_no", emp_no);
	    if(t_bool) q.setParameter("title", title);
	    if (fd_bool) q.setParameter("from_date", from_date);
	    if (td_bool)q.setParameter("to_date", to_date);
	    q.setMaxResults(20);
	    log.info("Query invocata: " + hql.toString());
	    List res = q.getResultList();
	    for (Object o : res) {
	      Titles d = (Titles) o;
	      Employees e=d.getEmployees();
	      SimpleEmployee se=new SimpleEmployee(e.getEmpNo(), e.getBirthDate(), e.getFirstName(), e.getLastName(), e.getGender(), e.getHireDate());
	      TitlesOutput to=new TitlesOutput(d.getId().getTitle(), d.getId().getFromDate(), se);
	      log.info("Selected title: " + to.getTitle() + "For employee: " + to.getSe().getEmpNo());
	      result.add(to);
	    }
	    return result;
	  }

	public List<TitleCountOutput> TitleCount() {
		// TODO Auto-generated method stub
				ArrayList<TitleCountOutput> result = new ArrayList<TitleCountOutput>();
				Session session = factory.openSession();
			    Transaction tx = null;
			    tx = session.beginTransaction();
			    log.info("Getting title count");
			    String hql = "SELECT t.id.title, count(*) FROM Employees e INNER JOIN e.titleses t GROUP BY t.id.title";
			    Query q=session.createQuery(hql);
			    q.setMaxResults(20);
			    log.info("Query invocata: " + hql.toString());
			    List res=q.getResultList();
			    for(Object r: res){
				      Object[] row = (Object[]) r;
				      String title=(String) row[0];
				      long count=(long) row[1];
				      TitleCountOutput tco=new TitleCountOutput(title,count);
				      result.add(tco);
				    }
			    return result;
			}
	}
