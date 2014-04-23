package westbahn.model;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Inheritance()
public class Zeitkarte extends Ticket 
{
	@NotNull
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
