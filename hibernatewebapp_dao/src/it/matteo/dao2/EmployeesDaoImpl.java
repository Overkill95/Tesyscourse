package it.matteo.dao2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;
import org.hibernate.Criteria;
import org.hibernate.criterion.*;
import org.hibernate.HibernateException;
import org.hibernate.query.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.jws.WebService;

import com.mysql.jdbc.StringUtils;

import it.matteo.vo2.Employees;
//@WebService(endpointInterface="it.matteo.dao.DepartmentManagerDao")
public class EmployeesDaoImpl implements EmployeesDao{
	private static final Logger log=Logger.getLogger(EmployeesDaoImpl.class);
	private static SessionFactory factory; 
	public EmployeesDaoImpl() {
		super();
		try {
	         factory = new Configuration().configure().buildSessionFactory();
	      } catch (Throwable ex) { 
	         log.error("Failed to create sessionFactory object." + ex);
	         throw new ExceptionInInitializerError(ex); 
	      }
	}
	@Override
	public void insertEmployees(Employees o) {
		  Session session = EmployeesDaoImpl.factory.openSession();
	      Transaction tx = null;
	      try {
	    	  tx = session.beginTransaction();
	    	  session.save(o); 
	    	  tx.commit();
	      }
	      catch(HibernateException e) {
	    	  if (tx!=null) tx.rollback();
	          log.error(e);
	      }
	      finally {
	    	  session.close();
	      }
	}
	@Override
	public void removeEmployee(int emp_no) {
		Session session = EmployeesDaoImpl.factory.openSession();
	    Transaction tx = null;
	    String hql = "DELETE FROM Employees WHERE emp_no = :emp_no";
	    Query q=session.createQuery(hql);
	    q.setParameter("emp_no", emp_no);
	    int ret=q.executeUpdate();
	    if(ret<1) {
	    	log.info("No rows deleted");
	    }
	    else {
	    	log.info("Rows deleted: " + ret);
	    }
	}
	@Override
	public  void updateEmployee(int pk, Integer emp_no, String bd, String fn, String ln, String g, String hd) {
		Session session=factory.openSession();
		Transaction tx = null;
		String hql = constructString(emp_no, bd, fn, ln, g, hd);
		Query q=session.createQuery(hql);
		int ret=execUpdate(q, emp_no, bd, fn, ln, g, hd,pk);
		 if(ret<1) {
		    	log.info("No rows updated");
		    }
		    else {
		    	log.info("Rows updated: " + ret);
		    }
	}
	@Override
	public List<Employees> selectEmployee(Integer emp_no, String bd, String fn, String ln, String g, String hd) {
		ArrayList<Employees> res=new ArrayList<Employees>();
		Session session=factory.openSession();
		Criterion emp_no_crit=null;
		Criterion bd_crit=null;
		Criterion fn_crit=null;
		Criterion ln_crit=null;
		Criterion g_crit=null;
		Criterion hd_crit=null;
		Transaction tx = null;
		boolean emp_no_bool=emp_no!=null;
		boolean bd_bool = bd!=null;
		boolean fn_bool=fn!=null;
		boolean ln_bool=ln!=null;
		boolean gn_bool=g!=null;
		boolean hd_bool=hd!=null;
		Criteria cr = session.createCriteria(Employees.class);
		if(emp_no_bool) emp_no_crit=Restrictions.eq("emp_no", emp_no);
		if(bd_bool) bd_crit=Restrictions.eq("birth_date", bd);
		if(fn_bool) bd_crit=Restrictions.eq("first_name", fn);
		if(ln_bool) bd_crit=Restrictions.eq("last_name", ln);
		if(gn_bool) bd_crit=Restrictions.eq("gender", g);
		if(hd_bool) bd_crit=Restrictions.eq("hire_date", hd);
		Conjunction conj = Restrictions.and(emp_no_crit, fn_crit, ln_crit, g_crit, hd_crit);
		cr.add(conj);
		List result=cr.list();
		for(Object o: result) {
			Employees e = (Employees) o;
			res.add(e);
		}
		return res;
	}
	public static String constructString(Integer emp_no, String bd, String fn, String ln, String g, String hd) {
		String query="UPDATE Employees";
		if(emp_no!=null) {
			query += " SET emp_no = :emp_no";
		}
		if(bd!=null) {
			query +=", birth_date = :birth_date";
		}
		if(fn!=null) {
			query +=", first_name = :first_name";
		}
		if(ln!=null) {
			query += ", last_name = :last_name";
		}
		if(g!=null) {
			query += ", gender = :gender";
		}
		if(hd!=null) {
			query += ", hire_date = :hire_date";
		}
		return query += " WHERE emp_no = :emp_no";
	}
	public int execUpdate(Query q, Integer emp_no, String bd, String fn, String ln, String g, String hd, int pk) {
		String query="UPDATE Employees";
			if(emp_no!=null) {
				q.setParameter("emp_no", emp_no);
			}
			if(bd!=null) {
				q.setParameter("birth_date", bd);
			}
			if(fn!=null) {
				q.setParameter("first_name", fn);
			}
			if(ln!=null) {
				q.setParameter("last_name", ln);
			}
			if(g!=null) {
				q.setParameter("gender", g);
			}
			if(hd!=null) {
				q.setParameter("hire_date", hd);
			}
			q.setParameter("hire_date", hd);
			return q.executeUpdate();
	}
}