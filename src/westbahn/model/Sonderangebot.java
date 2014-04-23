package westbahn.model;

import java.util.Date;
import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;

@Entity
public class Sonderangebot 
{
	@Id
	private Long ID;

	private int kontingent = 999;
	
	@NotNull
	@Future
	private Date startZeit;

	private int dauer = 12;

	private float preisNachlass = 0.5f;

	@OneToMany
	private List<Ticket> tickets;

	public Sonderangebot() 
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

	public int getKontingent() 
	{
		return kontingent;
	}

	public void setKontingent(int kontingent) 
	{
		this.kontingent = kontingent;
	}

	public Date getStartZeit() 
	{
		return startZeit;
	}

	public void setStartZeit(Date startZeit) 
	{
		this.startZeit = startZeit;
	}

	public int getDauer() 
	{
		return dauer;
	}

	public void setDauer(int dauer)
	{
		this.dauer = dauer;
	}

	public float getPreisNachlass() 
	{
		return preisNachlass;
	}

	public void setPreisNachlass(float preisNachlass) 
	{
		this.preisNachlass = preisNachlass;
	}

	public List<Ticket> getTickets()
	{
		return tickets;
	}

	public void setTickets(List<Ticket> tickets) 
	{
		this.tickets = tickets;
	}
}
