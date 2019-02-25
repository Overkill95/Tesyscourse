package it.hibernate;
// Generated 20-feb-2019 10.46.01 by Hibernate Tools 5.0.6.Final

//import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
//import org.apache.commons.logging.Log;
//import org.apache.commons.logging.LogFactory;

import org.apache.log4j.Logger;

//import it.matteo.hibernatemaven.dao.EmployeesDaoImpl;

/**
 * Home object for domain model class CurrentDeptEmp.
 * @see it.hibernate.CurrentDeptEmp
 * @author Hibernate Tools
 */
//@Stateless
public class CurrentDeptEmpHome {

	private static final Logger log=Logger.getLogger(CurrentDeptEmpHome.class);

	@PersistenceContext
	private EntityManager entityManager;

	public void persist(CurrentDeptEmp transientInstance) {
		log.debug("persisting CurrentDeptEmp instance");
		try {
			entityManager.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void remove(CurrentDeptEmp persistentInstance) {
		log.debug("removing CurrentDeptEmp instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	public CurrentDeptEmp merge(CurrentDeptEmp detachedInstance) {
		log.debug("merging CurrentDeptEmp instance");
		try {
			CurrentDeptEmp result = entityManager.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public CurrentDeptEmp findById(CurrentDeptEmpId id) {
		log.debug("getting CurrentDeptEmp instance with id: " + id);
		try {
			CurrentDeptEmp instance = entityManager.find(CurrentDeptEmp.class, id);
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
}
