package westbahn.model;

import javax.persistence.*;

@NamedQueries(
       @NamedQuery(name = "getConnectionWithoutReservations",
                query = "FROM Ticket t " +
                        "INNER JOIN t.strecke s " +
                        "WHERE s.start = :start " +
                        "AND s.ende = :ende " +
                        "AND 0 = (SELECT COUNT(r) FROM Reservierung r " +
                                    "WHERE r.strecke = s)")
)
@Entity
public abstract class Ticket 
{
	@Id
	protected Long ID;
	
	@OneToOne(optional = false)
	protected Strecke strecke;
	
	@Transient
	protected Zahlung zahlung;

	public Long getID() 
	{
		return ID;
	}

	public void setID(Long iD)
	{
		ID = iD;
	}

	public Strecke getStrecke() 
	{
		return strecke;
	}

	public void setStrecke(Strecke strecke) 
	{
		this.strecke = strecke;
	}

	public Zahlung getZahlung() 
	{
		return zahlung;
	}

	public void setZahlung(Zahlung zahlung) 
	{
		this.zahlung = zahlung;
	}
}
