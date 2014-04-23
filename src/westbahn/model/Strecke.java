package westbahn.model;

import javax.persistence.*;

import westbahn.BahnhofValidator;

@Entity
@BahnhofValidator(bahnhof1 = "start", bahnhof2 = "ende")
public class Strecke 
{
	@Id
	private Long ID;
	
	@OneToOne(optional = false)
	private Bahnhof start;

	@OneToOne(optional = false)
	private Bahnhof ende;
	
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
