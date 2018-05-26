package de.rf4.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(catalog = "PUBLIC")
public class Wetter
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String art;
    
    public String getArt()
    {
        return art;
    }
    
    public long getId()
    {
        return id;
    }
    
    public void setArt(String art)
    {
        this.art = art;
    }
    
    public void setId(long id)
    {
        this.id = id;
    }
    
}
