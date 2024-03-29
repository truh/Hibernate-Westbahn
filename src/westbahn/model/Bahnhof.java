package westbahn.model;

import javax.persistence.*;
import javax.validation.constraints.*;

/**
 * Bahnhof Entity
 * 
 * @author Jakob Klepp
 * @author Andreas Willinger
 * @version 20140424
 */
@Entity
public class Bahnhof 
{
    public Bahnhof() {}

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private Long ID;

    @Column(unique = true)
    @Pattern(regexp = "[0-9A-Za-z-]{2,150}")
	private String name;

	private int absPreisEntfernung;
	private int absKmEntfernung;
	private int absZeitEntfernung;
	private boolean kopfBahnhof = false;

    public Long getID() 
    {
        return ID;
    }

    public void setID(Long ID) 
    {
        this.ID = ID;
    }

    public String getName() 
    {
        return name;
    }

    public void setName(String name) 
    {
        this.name = name;
    }

    public int getAbsPreisEntfernung() 
    {
        return absPreisEntfernung;
    }

    public void setAbsPreisEntfernung(int absPreisEntfernung) 
    {
        this.absPreisEntfernung = absPreisEntfernung;
    }

    public int getAbsKmEntfernung() 
    {
        return absKmEntfernung;
    }

    public void setAbsKmEntfernung(int absKmEntfernung) 
    {
        this.absKmEntfernung = absKmEntfernung;
    }

    public int getAbsZeitEntfernung() 
    {
        return absZeitEntfernung;
    }

    public void setAbsZeitEntfernung(int absZeitEntfernung) 
    {
        this.absZeitEntfernung = absZeitEntfernung;
    }

    public boolean isKopfBahnhof() 
    {
        return kopfBahnhof;
    }

    public void setKopfBahnhof(boolean kopfBahnhof) 
    {
        this.kopfBahnhof = kopfBahnhof;
    }

    public Long getGesamtPraemienMeilen() 
    {
        return 0L;
    }
}
