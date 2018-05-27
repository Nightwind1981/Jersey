package rf4.fangbuch.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(catalog = "PUBLIC")
public class Fisch
{
    @Id
    @Column(length = 30)
    private String bezeichnung;
    
    @Column(length = 6)
    private int gewichtWertig;

    @Column(length = 6)
    private int gewichtTrophy;

    @Column(length = 6)
    private int gewichtSuperTrophy;
    
    @ManyToMany
    @JoinTable(name = "FISCHEZUSEEN", joinColumns =
    { @JoinColumn(name = "BEZEICHNUNG") }, inverseJoinColumns =
    { @JoinColumn(name = "NAME") })
    private List<See> seen = new ArrayList<>();
    
    public void addSee(See see)
    {
        seen.add(see);
    }
    
    public String getBezeichnung()
    {
        return bezeichnung;
    }
    
    public int getGewichtSuperTrophy()
    {
        return gewichtSuperTrophy;
    }
    
    public int getGewichtTrophy()
    {
        return gewichtTrophy;
    }
    
    public int getGewichtWertig()
    {
        return gewichtWertig;
    }
    
    public List<See> getSeen()
    {
        return seen;
    }
    
    public void setBezeichnung(String name)
    {
        this.bezeichnung = name;
    }
    
    public void setGewichtSuperTrophy(int gewichtSuperTrophy)
    {
        this.gewichtSuperTrophy = gewichtSuperTrophy;
    }
    
    public void setGewichtTrophy(int gewichtTrophy)
    {
        this.gewichtTrophy = gewichtTrophy;
    }
    
    public void setGewichtWertig(int gewichtWertig)
    {
        this.gewichtWertig = gewichtWertig;
    }

}
