package westbahn.model;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import java.util.Collection;


@NamedQueries(value = {
        @NamedQuery(name = "getAllReservations",
                query = "FROM Benutzer WHERE eMail = :emailAddress"),
        @NamedQuery(name = "getPassengersWithMonthlyPass",
                query = "SELECT DISTINCT b FROM Benutzer b " +
                        "INNER JOIN Zeitkarte " +
                        "WHERE Zeitkarte.typ = 'MONATSKARTE'")
})
@Entity
public class Benutzer {
    @Id
	private Long ID;

	private String vorName;

	private String nachName;
	
	@Pattern(regexp = "^([a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+)@([a-zA-Z0-9-\\.?]+)\\.([a-zA-Z0-9-]+)")
	private String eMail;

	private String passwort;

	private String smsNummer;

	private Long verbuchtePraemienMeilen;

    @OneToMany
	private Collection<Ticket> tickets;

    @OneToMany(mappedBy = "benutzer")
	private Collection<Reservierung> reservierungen;

    public Long getID() {
        return ID;
    }

    public void setID(Long ID) {
        this.ID = ID;
    }

    public String getVorName() {
        return vorName;
    }

    public void setVorName(String vorName) {
        this.vorName = vorName;
    }

    public String getNachName() {
        return nachName;
    }

    public void setNachName(String nachName) {
        this.nachName = nachName;
    }

    public String geteMail() {
        return eMail;
    }

    public void seteMail(String eMail) {
        this.eMail = eMail;
    }

    public String getPasswort() {
        return passwort;
    }

    public void setPasswort(String passwort) {
        this.passwort = passwort;
    }

    public String getSmsNummer() {
        return smsNummer;
    }

    public void setSmsNummer(String smsNummer) {
        this.smsNummer = smsNummer;
    }

    public Long getVerbuchtePraemienMeilen() {
        return verbuchtePraemienMeilen;
    }

    public void setVerbuchtePraemienMeilen(Long verbuchtePraemienMeilen) {
        this.verbuchtePraemienMeilen = verbuchtePraemienMeilen;
    }

    public Collection<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(Collection<Ticket> tickets) {
        this.tickets = tickets;
    }

    public Collection<Reservierung> getReservierungen() {
        return reservierungen;
    }

    public void setReservierungen(Collection<Reservierung> reservierungen) {
        this.reservierungen = reservierungen;
    }
}
