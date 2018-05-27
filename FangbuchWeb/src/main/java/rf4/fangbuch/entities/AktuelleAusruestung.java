package rf4.fangbuch.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(catalog = "PUBLIC")
public class AktuelleAusruestung
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    
    @ManyToOne
    @JoinColumn(referencedColumnName = "ID")
    private Ausruestung ausruestung;
    
    @ManyToOne
    @JoinColumn(referencedColumnName = "ID")
    private Montage montage;
    
    @ManyToOne
    @JoinColumn(referencedColumnName = "ID")
    private Koeder koeder;
    
    public Ausruestung getAusruestung()
    {
        return ausruestung;
    }
    
    public long getId()
    {
        return id;
    }
    
    public Koeder getKoeder()
    {
        return koeder;
    }
    
    public Montage getMontage()
    {
        return montage;
    }
    
    public void setAusruestung(Ausruestung ausruestung)
    {
        this.ausruestung = ausruestung;
    }
    
    public void setId(long id)
    {
        this.id = id;
    }
    
    public void setKoeder(Koeder koeder)
    {
        this.koeder = koeder;
    }
    
    public void setMontage(Montage montage)
    {
        this.montage = montage;
    }
    
}
