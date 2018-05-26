package entities;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManager;

import de.rf4.em.EntityManagerUtil;
import de.rf4.entities.AktuelleAusruestung;
import de.rf4.entities.Ausruestung;
import de.rf4.entities.Fang;
import de.rf4.entities.Fisch;
import de.rf4.entities.Koeder;
import de.rf4.entities.Montage;
import de.rf4.entities.See;
import de.rf4.entities.Session;
import de.rf4.entities.Spot;
import de.rf4.entities.Wetter;

//Hibernate JPA With HSQL Example

public class JPASeen
{
    
    public static void main(String[] args)
    {
        JPASeen example = new JPASeen();
        System.out.println("After Sucessfully See insertion ");
        See see1 = example.saveSee("Sura");
        See see2 = example.saveSee("großer Strom");
        
        System.out.println("After Sucessfully See modification ");
        example.updateSee(see1.getName(), 24);
        example.updateSee(see2.getName(), 20);
        example.listSeenFischeSpots();
        
        System.out.println("After Sucessfully Spot insert ");
        Spot spot = example.saveSpot("Test 1", see1);
        Spot spot2 = example.saveSpot("Test 2", see1);
        
        System.out.println("After Sucessfully Fisch insert ");
        Fisch fisch = example.saveFisch("Aland", see1);
        fisch = example.saveFisch("Brasse", see1);
        example.listSeenFischeSpots();
        
        System.out.println("After Sucessfully Koeder insert ");
        Koeder koeder = example.saveKoeder("Tauwurm");
        koeder = example.saveKoeder("Erbsenbrei");
        example.listKoeder();
        
        System.out.println("After Sucessfully Ausruestung insert ");
        Ausruestung ausruestung = example.saveAusruestung("Test", "Rute, Rolle, ...");
        ausruestung = example.saveAusruestung("Test", "Rute, Rolle, ...");
        
        System.out.println("After Sucessfully Montage insert ");
        Montage montage = example.saveMontage("Schlaufe", 1);
        montage = example.saveMontage("einfache Grund", 3);
        
        System.out.println("After Sucessfully Aktuelle Ausruestung insert ");
        AktuelleAusruestung aktausr = example.saveAktuelleAusruestung(ausruestung, koeder, montage);
        
        System.out.println("After Sucessfully Aktuelle Session insert ");
        Session session = example.saveSession("Dominic", new Date(), 19, 00, Arrays.asList(aktausr));
        
        System.out.println("After Sucessfully Aktuelle Wetter insert ");
        Wetter wetter = example.saveWetter("Sonnig");
        wetter = example.saveWetter("Bewölkt");

        System.out.println("After Sucessfully Fang insert ");
        Fang fang = example.saveFang(fisch, 1500, new Date(), aktausr, spot, wetter, session);
        fang = example.saveFang(fisch, 1501, new Date(), aktausr, spot, wetter, session);
        fang = example.saveFang(fisch, 1502, new Date(), aktausr, spot, wetter, session);
        example.listFaenge();
        
        System.out.println("After Sucessfully Spot delete ");
        example.deleteSpot(spot2.getName());
        System.out.println("After Sucessfully See deletion ");
        example.deleteSee(see2.getName());
        example.listSeenFischeSpots();
        
        example.close();
    }
    
    private EntityManager entityManager = EntityManagerUtil.getEntityManager();

    private void close()
    {
        entityManager.close();
        EntityManagerUtil.close();
    }
    
    public void deleteSee(String name)
    {
        try
        {
            entityManager.getTransaction().begin();
            See see = entityManager.find(See.class, name);
            entityManager.remove(see);
            entityManager.getTransaction().commit();
        }
        catch (Exception e)
        {
            entityManager.getTransaction().rollback();
        }
    }
    
    private void deleteSpot(String name)
    {
        try
        {
            entityManager.getTransaction().begin();
            Spot spot = entityManager.find(Spot.class, name);
            entityManager.remove(spot);
            entityManager.getTransaction().commit();
        }
        catch (Exception e)
        {
            entityManager.getTransaction().rollback();
        }
    }

    public void listFaenge()
    {
        try
        {
            System.out.println("----");
            entityManager.getTransaction().begin();
            @SuppressWarnings("unchecked")
            List<Session> sessions = entityManager.createQuery("from Session").getResultList();
            for (Session session : sessions)
            {
                System.out.println("Session: " + session.getId() + " Benutzer: " + session.getBenutzer() + " Ausruestunggen "
                        + session.getAktuelleAusrüstung().size() + " Anzahl Fische: " + session.getFaenge().size());
                for (Fang fang : session.getFaenge())
                {
                    System.out.println(fang.getFisch().getBezeichnung() + " Spot: " + fang.getSpot().getName() + " Koeder: "
                            + fang.getAktuelleAusruestung().getKoeder().getName() + " Wetter: " + fang.getWetter().getArt());
                }
            }
            entityManager.getTransaction().commit();
        }
        catch (Exception e)
        {
            entityManager.getTransaction().rollback();
        }
    }

    public void listKoeder()
    {
        try
        {
            System.out.println("----");
            entityManager.getTransaction().begin();
            @SuppressWarnings("unchecked")
            List<Koeder> koeder = entityManager.createQuery("from Koeder").getResultList();
            for (Koeder koed : koeder)
            {
                System.out.println(koed.getName());
            }
            entityManager.getTransaction().commit();
        }
        catch (Exception e)
        {
            entityManager.getTransaction().rollback();
        }
    }

    public void listSeenFischeSpots()
    {
        try
        {
            System.out.println("----");
            entityManager.getTransaction().begin();
            @SuppressWarnings("unchecked")
            List<See> sees = entityManager.createQuery("from See").getResultList();
            for (Iterator<See> iterator = sees.iterator(); iterator.hasNext();)
            {
                See see = iterator.next();
                System.out.println(see.getName() + " " + see.getMinLevel() + " Spots: " + see.getSpots().size());
                if (see.getSpots() != null)
                {
                    for (Spot spot : see.getSpots())
                    {
                        System.out.println("\t- " + spot.getName());
                    }
                }
                if (see.getFische() != null)
                {
                    for (Fisch fisch : see.getFische())
                    {
                        System.out.println("Fisch: " + fisch.getBezeichnung());
                    }
                }
            }
            entityManager.getTransaction().commit();
        }
        catch (Exception e)
        {
            entityManager.getTransaction().rollback();
        }
    }

    private AktuelleAusruestung saveAktuelleAusruestung(Ausruestung ausruestung, Koeder koeder, Montage montage)
    {
        AktuelleAusruestung aktuAusruestung = new AktuelleAusruestung();
        try
        {
            entityManager.getTransaction().begin();
            ausruestung = entityManager.merge(ausruestung);
            koeder = entityManager.merge(koeder);
            montage = entityManager.merge(montage);

            aktuAusruestung.setAusruestung(ausruestung);
            aktuAusruestung.setKoeder(koeder);
            aktuAusruestung.setMontage(montage);
            aktuAusruestung = entityManager.merge(aktuAusruestung);
            
            entityManager.getTransaction().commit();
        }
        catch (Exception e)
        {
            e.printStackTrace();
            entityManager.getTransaction().rollback();
        }
        return aktuAusruestung;
    }
    
    private Ausruestung saveAusruestung(String name, String bemerkung)
    {
        Ausruestung ausruestung = new Ausruestung();
        try
        {
            entityManager.getTransaction().begin();
            ausruestung.setName(name);
            ausruestung.setBemerkung(bemerkung);
            ausruestung = entityManager.merge(ausruestung);
            
            entityManager.getTransaction().commit();
        }
        catch (Exception e)
        {
            e.printStackTrace();
            entityManager.getTransaction().rollback();
        }
        return ausruestung;
    }
    
    private Fang saveFang(Fisch fisch, int gewicht, Date zeitpunkt, AktuelleAusruestung aktuelleAusruestung, Spot spot, Wetter wetter,
            Session session)
    {
        Fang fang = new Fang();
        try
        {
            entityManager.getTransaction().begin();
            fisch = entityManager.merge(fisch);
            aktuelleAusruestung = entityManager.merge(aktuelleAusruestung);
            spot = entityManager.merge(spot);
            wetter = entityManager.merge(wetter);
            session = entityManager.merge(session);
            
            fang.setFisch(fisch);
            fang.setFangzeit(zeitpunkt);
            fang.setErstelldatum(zeitpunkt);
            fang.setGewicht(gewicht);
            fang.setAktuelleAusruestung(aktuelleAusruestung);
            fang.setSpot(spot);
            fang.setWetter(wetter);
            fang.setSession(session);
            fang = entityManager.merge(fang);

            session.getFaenge().add(fang);
            
            entityManager.getTransaction().commit();
        }
        catch (Exception e)
        {
            e.printStackTrace();
            entityManager.getTransaction().rollback();
        }
        return fang;
    }

    private Fisch saveFisch(String bez, See see)
    {
        Fisch fisch = null;
        try
        {
            entityManager.getTransaction().begin();
            see = entityManager.merge(see);
            fisch = new Fisch();
            fisch.setBezeichnung(bez);
            fisch.addSee(see);
            fisch = entityManager.merge(fisch);
            see.getFische().add(fisch);
            entityManager.getTransaction().commit();
        }
        catch (Exception e)
        {
            e.printStackTrace();
            entityManager.getTransaction().rollback();
        }
        return fisch;
    }

    private Koeder saveKoeder(String name)
    {
        Koeder koeder = new Koeder();
        try
        {
            entityManager.getTransaction().begin();
            koeder.setName(name);
            koeder = entityManager.merge(koeder);
            entityManager.getTransaction().commit();
        }
        catch (Exception e)
        {
            entityManager.getTransaction().rollback();
        }

        return koeder;
    }
    
    private Montage saveMontage(String name, int punkte)
    {
        Montage montage = new Montage();
        try
        {
            entityManager.getTransaction().begin();
            montage.setName(name);
            montage.setPunkte(punkte);
            montage = entityManager.merge(montage);
            
            entityManager.getTransaction().commit();
        }
        catch (Exception e)
        {
            e.printStackTrace();
            entityManager.getTransaction().rollback();
        }
        return montage;
    }
    
    public See saveSee(String seeName)
    {
        See see = new See();
        try
        {
            entityManager.getTransaction().begin();
            see.setName(seeName);
            see = entityManager.merge(see);
            entityManager.getTransaction().commit();
        }
        catch (Exception e)
        {
            entityManager.getTransaction().rollback();
        }
        return see;
    }
    
    private Session saveSession(String benutzer, Date start, int stunden, int minuten, List<AktuelleAusruestung> asList)
    {
        Session session = new Session();
        try
        {
            entityManager.getTransaction().begin();
            for (AktuelleAusruestung akt : asList)
            {
                akt = entityManager.merge(akt);
                session.getAktuelleAusrüstung().add(akt);
            }
            session.setBenutzer(benutzer);
            session.setStart(start);
            session.setIgzeit(Date.from(LocalDateTime.now().truncatedTo(ChronoUnit.DAYS).withHour(stunden).withMinute(minuten)
                    .atZone(ZoneId.systemDefault()).toInstant()));
            session = entityManager.merge(session);
            entityManager.getTransaction().commit();
        }
        catch (Exception e)
        {
            e.printStackTrace();
            entityManager.getTransaction().rollback();
        }
        return session;
    }
    
    private Spot saveSpot(String name, See see)
    {
        Spot spot = null;
        try
        {
            entityManager.getTransaction().begin();
            see = entityManager.merge(see);
            spot = new Spot();
            spot.setName(name);
            spot.setSee(see);
            spot = entityManager.merge(spot);
            see.addSpot(spot);
            entityManager.getTransaction().commit();
        }
        catch (Exception e)
        {
            e.printStackTrace();
            entityManager.getTransaction().rollback();
        }
        return spot;
    }
    
    private Wetter saveWetter(String artg)
    {
        Wetter wetter = new Wetter();
        try
        {
            entityManager.getTransaction().begin();
            wetter.setArt(artg);
            wetter = entityManager.merge(wetter);
            entityManager.getTransaction().commit();
        }
        catch (Exception e)
        {
            e.printStackTrace();
            entityManager.getTransaction().rollback();
        }
        return wetter;
    }
    
    public void updateSee(String name, int level)
    {
        try
        {
            entityManager.getTransaction().begin();
            See see = entityManager.find(See.class, name);
            see.setMinLevel(level);
            entityManager.getTransaction().commit();
        }
        catch (Exception e)
        {
            entityManager.getTransaction().rollback();
        }
    }
}