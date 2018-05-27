package de.rf4.em;

import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import de.dillinger.verladung.VLB_TRB_Exception;
import de.dillinger.verladung.VLB_TRB_Logger;

public class JPAUtilities<T>
{
    private static final String QUERYBEZEICHNUNG_NICHT_GESETZT = "Querybezeichnung nicht gesetzt";
    private static final String ENTITYMANANGER_NICHT_GESETZT = "Entitymananger nicht gesetzt";
    private static final VLB_TRB_Logger log = VLB_TRB_Logger.getVLBLogger(JPAUtilities.class);

    public List<T> abfrageNamedquery(EntityManager em, String queryname, Map<String, Object> parameter, String logmsg) throws VLB_TRB_Exception
    {
        checkParameter(em, queryname);
        try
        {
            log.methodstart(logmsg);
            Query query = em.createNamedQuery(queryname);
            setParameter(queryname, parameter, query);
            return query.getResultList();
        }
        finally
        {
            log.methodend(logmsg);
        }
    }

    public List<T> abfrageNamedquery(EntityManager em, String queryname, String logmsg) throws VLB_TRB_Exception
    {
        return abfrageNamedquery(em, queryname, null, logmsg);
    }

    public T abfrageNamedquerySingleResult(EntityManager em, String queryname, Map<String, Object> parameter, String logmsg) throws VLB_TRB_Exception
    {
        checkParameter(em, queryname);
        try
        {
            log.methodstart(logmsg);
            Query query = em.createNamedQuery(queryname);

            setParameter(queryname, parameter, query);

            return (T) query.getSingleResult();
        }
        catch (NoResultException e)
        {
            return null;
        }
        finally
        {
            log.methodend(logmsg);
        }
    }

    private void checkParameter(EntityManager em, String queryname) throws VLB_TRB_Exception
    {
        if (em == null)
        {
            log.error(ENTITYMANANGER_NICHT_GESETZT);
            throw new VLB_TRB_Exception(ENTITYMANANGER_NICHT_GESETZT);
        }
        if (queryname == null)
        {
            log.error(QUERYBEZEICHNUNG_NICHT_GESETZT);
            throw new VLB_TRB_Exception(QUERYBEZEICHNUNG_NICHT_GESETZT);
        }
    }

    private void setParameter(String queryname, Map<String, Object> parameter, Query query) throws VLB_TRB_Exception
    {
        if (query == null)
        {
            throw new VLB_TRB_Exception("Query " + queryname + " nicht vorhanden.");
        }
        if (parameter != null && !parameter.isEmpty())
        {
            for (String key : parameter.keySet())
            {
                log.info("Parameter: " + key + " Wert: " + parameter.get(key));
                query.setParameter(key, parameter.get(key));
            }
        }
    }
}
