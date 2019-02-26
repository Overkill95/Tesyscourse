package it.hibernate;
// Generated 22-feb-2019 8.27.09 by Hibernate Tools 5.0.6.Final

//import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

//import org.apache.commons.logging.Log;
//import org.apache.commons.logging.LogFactory;
import org.apache.log4j.Logger;

/**
 * Home object for domain model class DeptEmpLatestDate.
 * @see it.hibernate.DeptEmpLatestDate
 * @author Hibernate Tools
 */
//@Stateless
public class DeptEmpLatestDateHome {

	  private static final Logger log = Logger.getLogger(DeptEmpLatestDateHome.class);

	@PersistenceContext
	private EntityManager entityManager;

	public void persist(DeptEmpLatestDate transientInstance) {
		log.debug("persisting DeptEmpLatestDate instance");
		try {
			entityManager.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void remove(DeptEmpLatestDate persistentInstance) {
		log.debug("removing DeptEmpLatestDate instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	public DeptEmpLatestDate merge(DeptEmpLatestDate detachedInstance) {
		log.debug("merging DeptEmpLatestDate instance");
		try {
			DeptEmpLatestDate result = entityManager.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public DeptEmpLatestDate findById(DeptEmpLatestDateId id) {
		log.debug("getting DeptEmpLatestDate instance with id: " + id);
		try {
			DeptEmpLatestDate instance = entityManager.find(DeptEmpLatestDate.class, id);
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
}
