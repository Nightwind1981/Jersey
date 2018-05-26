package de.rf4.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(catalog = "PUBLIC")
public class Fang
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    
    @ManyToOne
    @JoinColumn(referencedColumnName = "BEZEICHNUNG")
    private Fisch fisch;
    
    @ManyToOne
    @JoinColumn(referencedColumnName = "ID")
    private AktuelleAusruestung aktuelleAusruestung;
    
    private Integer gewicht;
    
    @Temporal(TemporalType.TIME)
    private Date fangzeit;
    
    @Temporal(TemporalType.TIMESTAMP)
    private Date erstelldatum;
    
    private String bemerkung;

    @ManyToOne
    @JoinColumn(referencedColumnName = "ID")
    private Spot spot;
    
    @ManyToOne
    @JoinColumn(referencedColumnName = "ID")
    private Session session;
    
    private Double temperatur;

    @ManyToOne
    @JoinColumn(referencedColumnName = "ID")
    private Wetter wetter;

    public AktuelleAusruestung getAktuelleAusruestung()
    {
        return aktuelleAusruestung;
    }

    public String getBemerkung()
    {
        return bemerkung;
    }
    
    public Date getErstelldatum()
    {
        return erstelldatum;
    }
    
    public Date getFangzeit()
    {
        return fangzeit;
    }
    
    public Fisch getFisch()
    {
        return fisch;
    }
    
    public Integer getGewicht()
    {
        return gewicht;
    }
    
    public long getId()
    {
        return id;
    }

    public Session getSession()
    {
        return session;
    }

    public Spot getSpot()
    {
        return spot;
    }

    public Double getTemperatur()
    {
        return temperatur;
    }
    
    public Wetter getWetter()
    {
        return wetter;
    }
    
    public void setAktuelleAusruestung(AktuelleAusruestung aktuelleAusruestung)
    {
        this.aktuelleAusruestung = aktuelleAusruestung;
    }

    public void setBemerkung(String bemerkung)
    {
        this.bemerkung = bemerkung;
    }

    public void setErstelldatum(Date erstelldatum)
    {
        this.erstelldatum = erstelldatum;
    }

    public void setFangzeit(Date fangzeit)
    {
        this.fangzeit = fangzeit;
    }

    public void setFisch(Fisch fisch)
    {
        this.fisch = fisch;
    }
    
    public void setGewicht(Integer gewicht)
    {
        this.gewicht = gewicht;
    }

    public void setId(long id)
    {
        this.id = id;
    }
    
    public void setSession(Session session)
    {
        this.session = session;
    }
    
    public void setSpot(Spot spot)
    {
        this.spot = spot;
    }
    
    public void setTemperatur(Double temperatur)
    {
        this.temperatur = temperatur;
    }

    public void setWetter(Wetter wetter)
    {
        this.wetter = wetter;
    }

}
