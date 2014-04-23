package westbahn.model;

import javax.persistence.Entity;
import java.util.Date;
import javax.persistence.*;

@Entity
public class Zeitkarte extends Ticket 
{
	private Date gueltigAb;
	private ZeitkartenTyp typ;

	public Date getGueltigAb() 
	{
		return gueltigAb;
	}

	public void setGueltigAb(Date gueltigAb) 
	{
		this.gueltigAb = gueltigAb;
	}

	public ZeitkartenTyp getTyp() 
	{
		return typ;
	}

	public void setTyp(ZeitkartenTyp typ) 
	{
		this.typ = typ;
	}
}
