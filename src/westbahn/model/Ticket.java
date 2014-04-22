package westbahn.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public abstract class Ticket {

    @Id
	protected Long ID;

	protected Strecke strecke;

	protected Zahlung zahlung;

}
