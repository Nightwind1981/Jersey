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
public class Spot
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private Integer x;
    private Integer y;
    private String name;
    private String bemerkung;
    @ManyToOne
    @JoinColumn(referencedColumnName = "NAME")
    private See see;

    public String getBemerkung()
    {
        return bemerkung;
    }

    public String getName()
    {
        return name;
    }
    
    public See getSee()
    {
        return see;
    }

    public Integer getX()
    {
        return x;
    }

    public Integer getY()
    {
        return y;
    }

    public void setBemerkung(String bemerkung)
    {
        this.bemerkung = bemerkung;
    }

    public void setName(String name)
    {
        this.name = name;
    }
    
    public void setSee(See see)
    {
        this.see = see;
    }

    public void setX(Integer x)
    {
        this.x = x;
    }

    public void setY(Integer y)
    {
        this.y = y;
    }

}
