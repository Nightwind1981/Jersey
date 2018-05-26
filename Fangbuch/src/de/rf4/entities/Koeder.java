package de.rf4.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(catalog = "PUBLIC")
public class Koeder
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String name;

    private String kategorie;

    public long getId()
    {
        return id;
    }

    public String getKategorie()
    {
        return kategorie;
    }

    public String getName()
    {
        return name;
    }

    public void setId(long id)
    {
        this.id = id;
    }

    public void setKategorie(String kategorie)
    {
        this.kategorie = kategorie;
    }

    public void setName(String name)
    {
        this.name = name;
    }

}
