package westbahn.model;

public class Benutzer {

	private Long ID;

	private String vorName;

	private String nachName;

	private String eMail;

	private String passwort;

	private String smsNummer;

	private Long verbuchtePraemienMeilen;

	private Ticket tickets;

	private Reservierung[] reservierungen;

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

    public Ticket getTickets() {
        return tickets;
    }

    public void setTickets(Ticket tickets) {
        this.tickets = tickets;
    }

    public Reservierung[] getReservierungen() {
        return reservierungen;
    }

    public void setReservierungen(Reservierung[] reservierungen) {
        this.reservierungen = reservierungen;
    }
}
