package westbahn.model;

import javax.persistence.Entity;

@Entity
public interface Zahlung {

	public void zahlungDurchfuehren();

}
