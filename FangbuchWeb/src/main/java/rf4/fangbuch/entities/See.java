package rf4.fangbuch.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(catalog = "PUBLIC")
public class See
{
    @Id
    @Column(length = 30)
    private String name;

    @Column(length = 2)
    private int minLevel;

    @OneToMany(mappedBy = "see")
    private List<Spot> spots = new ArrayList<>();
    
    @ManyToMany(mappedBy = "seen")
    private List<Fisch> fische = new ArrayList<>();

    public void addSpot(Spot spot)
    {
        spots.add(spot);
    }
    
    public List<Fisch> getFische()
    {
        return fische;
    }
    
    public int getMinLevel()
    {
        return minLevel;
    }
    
    public String getName()
    {
        return name;
    }

    public List<Spot> getSpots()
    {
        return spots;
    }
    
    public void setMinLevel(int minLevel)
    {
        this.minLevel = minLevel;
    }

    public void setName(String name)
    {
        this.name = name;
    }
}
