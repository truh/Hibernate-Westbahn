package westbahn.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import java.util.Date;
import westbahn.BahnhofValidator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Zug Entity
 * 
 * @author Andreas Willinger
 * @author Jakob Klepp
 * @version 20140424
 */
@Entity
// start trainstation != end transtation
@BahnhofValidator(bahnhof1 = "start", bahnhof2 = "ende")
public class Zug 
{
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private Long ID;

	@NotNull
	private Date startZeit;

	private int sitzPlaetze = 500;
	private int fahrradStellplaetze = 50;
	private int rollStuhlPlaetze = 10;
	
	@ManyToOne(optional = false)
	private Bahnhof start;
	
	@ManyToOne(optional = false)
	private Bahnhof ende;
	
	public Zug() {}

	public Long getID() 
	{
		return ID;
	}

	public void setID(Long iD) 
	{
		ID = iD;
	}

	public Date getStartZeit() 
	{
		return startZeit;
	}

	public void setStartZeit(Date startZeit) 
	{
		this.startZeit = startZeit;
	}

	public int getSitzPlaetze() 
	{
		return sitzPlaetze;
	}

	public void setSitzPlaetze(int sitzPlaetze) 
	{
		this.sitzPlaetze = sitzPlaetze;
	}

	public int getFahrradStellplaetze() 
	{
		return fahrradStellplaetze;
	}

	public void setFahrradStellplaetze(int fahrradStellplaetze) 
	{
		this.fahrradStellplaetze = fahrradStellplaetze;
	}

	public int getRollStuhlPlaetze() 
	{
		return rollStuhlPlaetze;
	}

	public void setRollStuhlPlaetze(int rollStuhlPlaetze) 
	{
		this.rollStuhlPlaetze = rollStuhlPlaetze;
	}

	public Bahnhof getStart() 
	{
		return start;
	}

	public void setStart(Bahnhof start) 
	{
		this.start = start;
	}

	public Bahnhof getEnde()
	{
		return ende;
	}

	public void setEnde(Bahnhof ende) 
	{
		this.ende = ende;
	}
}
