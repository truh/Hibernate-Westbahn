package westbahn.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.Collection;
import java.util.Date;

@Entity
public class Sonderangebot {

    @Id
	private Long ID;

	private int kontingent = 999;

	private Date startZeit;

	private int dauer = 12;

	private float preisNachlass = 0.5f;

    @OneToMany
	private Collection<Ticket> tickets;

    public Long getID() {
        return ID;
    }

    public void setID(Long ID) {
        this.ID = ID;
    }

    public int getKontingent() {
        return kontingent;
    }

    public void setKontingent(int kontingent) {
        this.kontingent = kontingent;
    }

    public Date getStartZeit() {
        return startZeit;
    }

    public void setStartZeit(Date startZeit) {
        this.startZeit = startZeit;
    }

    public int getDauer() {
        return dauer;
    }

    public void setDauer(int dauer) {
        this.dauer = dauer;
    }

    public float getPreisNachlass() {
        return preisNachlass;
    }

    public void setPreisNachlass(float preisNachlass) {
        this.preisNachlass = preisNachlass;
    }

    public Collection<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(Collection<Ticket> tickets) {
        this.tickets = tickets;
    }
}
