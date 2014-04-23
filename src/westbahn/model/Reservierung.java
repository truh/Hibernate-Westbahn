package westbahn.model;

import org.hibernate.annotations.ManyToAny;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

import java.util.Date;

@Entity
public class Reservierung {

    public Reservierung() {

    }

    @Id
	private Long ID;

	private Date datum;

	private int praemienMeilenBonus = 15;

	private int preis = 150;

	private StatusInfo status;
	
	@OneToOne(optional = false)
	private Zug zug;

    @OneToOne(optional = false)
	private Strecke strecke;

    @ManyToOne
	private Benutzer benutzer;

    //@OneToOne
    @Transient
	private Zahlung zahlung;

    public Long getID() {
        return ID;
    }

    public void setID(Long ID) {
        this.ID = ID;
    }

    public Date getDatum() {
        return datum;
    }

    public void setDatum(Date datum) {
        this.datum = datum;
    }

    public int getPraemienMeilenBonus() {
        return praemienMeilenBonus;
    }

    public void setPraemienMeilenBonus(int praemienMeilenBonus) {
        this.praemienMeilenBonus = praemienMeilenBonus;
    }

    public int getPreis() {
        return preis;
    }

    public void setPreis(int preis) {
        this.preis = preis;
    }

    public StatusInfo getStatus() {
        return status;
    }

    public void setStatus(StatusInfo status) {
        this.status = status;
    }

    public Zug getZug() {
        return zug;
    }

    public void setZug(Zug zug) {
        this.zug = zug;
    }

    public Strecke getStrecke() {
        return strecke;
    }

    public void setStrecke(Strecke strecke) {
        this.strecke = strecke;
    }

    public Benutzer getBenutzer() {
        return benutzer;
    }

    public void setBenutzer(Benutzer benutzer) {
        this.benutzer = benutzer;
    }

    public Zahlung getZahlung() {
        return zahlung;
    }

    public void setZahlung(Zahlung zahlung) {
        this.zahlung = zahlung;
    }
}
