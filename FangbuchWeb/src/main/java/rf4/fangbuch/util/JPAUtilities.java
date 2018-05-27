package rf4.fangbuch.util;

import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

public class JPAUtilities<T> {
	private static final String QUERYBEZEICHNUNG_NICHT_GESETZT = "Querybezeichnung nicht gesetzt";
	private static final String ENTITYMANANGER_NICHT_GESETZT = "Entitymananger nicht gesetzt";
	private static final Logger log = Logger.getLogger(JPAUtilities.class.getName());

	@SuppressWarnings("unchecked")
	public List<T> abfrageNamedquery(EntityManager em, String queryname, Map<String, Object> parameter, String logmsg)
			throws IllegalArgumentException {
		checkParameter(em, queryname);
		log.log(Level.INFO, logmsg);
		Query query = em.createNamedQuery(queryname);
		setParameter(queryname, parameter, query);
		return query.getResultList();
	}
	@SuppressWarnings("unchecked")
	public List<T> abfrageQuery(EntityManager em, String queryname, Map<String, Object> parameter, String logmsg)
			throws IllegalArgumentException {
		checkParameter(em, queryname);
		log.log(Level.INFO, logmsg);
		Query query = em.createQuery(queryname);
		setParameter(queryname, parameter, query);
		return query.getResultList();
	}

	public List<T> abfrageNamedquery(EntityManager em, String queryname, String logmsg)
			throws IllegalArgumentException {
		return abfrageNamedquery(em, queryname, null, logmsg);
	}

	@SuppressWarnings("unchecked")
	public T abfrageNamedquerySingleResult(EntityManager em, String queryname, Map<String, Object> parameter,
			String logmsg) throws IllegalArgumentException {
		checkParameter(em, queryname);
		try {
			log.log(Level.INFO, logmsg);
			Query query = em.createNamedQuery(queryname);

			setParameter(queryname, parameter, query);

			return (T) query.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	public T abfrageQuerySingleResult(EntityManager em, String queryname, Map<String, Object> parameter, String logmsg)
			throws IllegalArgumentException {
		checkParameter(em, queryname);
		try {
			log.log(Level.INFO, logmsg);
			Query query = em.createQuery(queryname);

			setParameter(queryname, parameter, query);

			return (T) query.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

	private void checkParameter(EntityManager em, String queryname) throws IllegalArgumentException {
		if (em == null) {
			log.log(Level.WARNING, ENTITYMANANGER_NICHT_GESETZT);
			throw new IllegalArgumentException(ENTITYMANANGER_NICHT_GESETZT);
		}
		if (queryname == null) {
			log.log(Level.WARNING, QUERYBEZEICHNUNG_NICHT_GESETZT);
			throw new IllegalArgumentException(QUERYBEZEICHNUNG_NICHT_GESETZT);
		}
	}

	private void setParameter(String queryname, Map<String, Object> parameter, Query query)
			throws IllegalArgumentException {
		if (query == null) {
			throw new IllegalArgumentException("Query " + queryname + " nicht vorhanden.");
		}
		if (parameter != null && !parameter.isEmpty()) {
			for (String key : parameter.keySet()) {
				log.info("Parameter: " + key + " Wert: " + parameter.get(key));
				query.setParameter(key, parameter.get(key));
			}
		}
	}
}
