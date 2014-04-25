package westbahn.model;

import java.util.Collection;

import javax.persistence.*;

import westbahn.BahnhofValidator;

/**
 * Strecke Entity
 * 
 * @author Andreas Willinger
 * @author Jakob Klepp
 * @version 20140424
 */
@Entity
// start trainstation != end trainstation
@BahnhofValidator(bahnhof1 = "start", bahnhof2 = "ende")
public class Strecke 
{
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long ID;
	
	@ManyToOne(optional = false)
	private Bahnhof start;

	@ManyToOne(optional = false)
	private Bahnhof ende;
	
	@OneToMany
	private Collection<Ticket> tickets;
	
	@OneToMany
	private Collection<Reservierung> reservierungen;
	
	public Strecke()
	{
		
	}

	public Long getID() 
	{
		return ID;
	}

	public void setID(Long iD) 
	{
		ID = iD;
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
