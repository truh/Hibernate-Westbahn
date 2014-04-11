package westbahn.model;

import javax.persistence.*;

public abstract class Ticket {
	@Id
	protected Long ID;

	protected Strecke strecke;

	protected Zahlung zahlung;

	public Long getID() {
		return ID;
	}

	public void setID(Long iD) {
		ID = iD;
	}

	public Strecke getStrecke() {
		return strecke;
	}

	public void setStrecke(Strecke strecke) {
		this.strecke = strecke;
	}

	public Zahlung getZahlung() {
		return zahlung;
	}

	public void setZahlung(Zahlung zahlung) {
		this.zahlung = zahlung;
	}
	
	

}
