package rf4.fangbuch.server;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import rf4.fangbuch.entities.AktuelleAusruestung;
import rf4.fangbuch.entities.Session;
import rf4.fangbuch.util.EntityManagerUtil;
import rf4.fangbuch.util.JPAUtilities;

@Stateless
public class SessionManager {

//	@Inject
//	  private EntityManager entityManager;
	private EntityManager entityManager = EntityManagerUtil.getEntityManager();

	private JPAUtilities<Session> jppSession = new JPAUtilities<>();
	
	private Logger log = Logger.getLogger(SessionManager.class.getName());

	public Session getOffeneSession(String benutzer) {

		Session session = null;
		try {
			entityManager.getTransaction().begin();

			Map<String, Object> params = new HashMap<>();
			params.put("benutzer", benutzer);

			session = jppSession.abfrageQuerySingleResult(entityManager, "from Session WHERE benutzer = :benutzer AND ende is NULL ",
					params, "getOffeneSessions");
			
			if (session == null)
			{
				session = new Session();
				session.setBenutzer("MeinName");
			}
			
			log.log(Level.INFO, "Session gefunden: " + session);
			
			entityManager.getTransaction().commit();
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
		}
		return session;
	}

	public Session speichereSession(String benutzer, Date start, int stunden, int minuten,
			List<AktuelleAusruestung> asList) {
		Session session = new Session();
		try {
			entityManager.getTransaction().begin();
			for (AktuelleAusruestung akt : asList) {
				akt = entityManager.merge(akt);
				session.getAktuelleAusrüstung().add(akt);
			}
			session.setBenutzer(benutzer);
			session.setStart(start);
			session.setIgzeit(Date.from(LocalDateTime.now().truncatedTo(ChronoUnit.DAYS).withHour(stunden)
					.withMinute(minuten).atZone(ZoneId.systemDefault()).toInstant()));
			session = entityManager.merge(session);
			entityManager.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			entityManager.getTransaction().rollback();
		}
		return session;
	}

}
