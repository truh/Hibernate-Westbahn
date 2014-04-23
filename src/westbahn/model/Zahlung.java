package westbahn.model;

import javax.persistence.MappedSuperclass;

@MappedSuperclass
public interface Zahlung
{
	public void zahlungDurchfuehren();
}
