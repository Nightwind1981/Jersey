package de.rf4.entities;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(catalog = "PUBLIC")
public class Session
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    
    private String benutzer;
    
    @Temporal(TemporalType.TIMESTAMP)
    private Date start;
    @Temporal(TemporalType.TIMESTAMP)
    private Date ende;
    
    @Temporal(TemporalType.TIME)
    private Date igzeit;
    
    @ManyToMany
    @JoinTable(name = "AUSRUESTUNGZUSESSION", joinColumns =
    { @JoinColumn(referencedColumnName = "id") }, inverseJoinColumns =
    { @JoinColumn(referencedColumnName = "id") })
    private List<AktuelleAusruestung> aktuelleAusrüstung = new ArrayList<>();
    
    @OneToMany(mappedBy = "session")
    private List<Fang> faenge = new ArrayList<>();
    
    public List<AktuelleAusruestung> getAktuelleAusrüstung()
    {
        return aktuelleAusrüstung;
    }
    
    public String getBenutzer()
    {
        return benutzer;
    }

    public Date getEnde()
    {
        return ende;
    }

    public List<Fang> getFaenge()
    {
        return faenge;
    }
    
    public long getId()
    {
        return id;
    }
    
    public Date getIgzeit()
    {
        return igzeit;
    }
    
    public Date getStart()
    {
        return start;
    }

    //    public void setAktuelleAusrüstung(List<AktuelleAusruestung> aktuelleAusrüstung)
    //    {
    //        this.aktuelleAusrüstung = aktuelleAusrüstung;
    //    }
    
    public void setBenutzer(String benutzer)
    {
        this.benutzer = benutzer;
    }
    
    public void setEnde(Date ende)
    {
        this.ende = ende;
    }

    //    public void setFaenge(List<Fang> faenge)
    //    {
    //        this.faenge = faenge;
    //    }
    
    public void setId(long id)
    {
        this.id = id;
    }
    
    public void setIgzeit(Date igzeit)
    {
        this.igzeit = igzeit;
    }
    
    public void setStart(Date start)
    {
        this.start = start;
    }
    
}
