package westbahn;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.AnnotationConfiguration;
import westbahn.model.*;
import westbahn.query.Inserts;

import javax.validation.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


public class Main
{

    private static final Logger log = Logger.getLogger(Main.class);

    static SimpleDateFormat dateForm = new SimpleDateFormat("dd.MM.yyyy");
    static SimpleDateFormat timeForm = new SimpleDateFormat("dd.MM.yyyy mm:hh");

    private static SessionFactory sessionFactory;
    private static Validator validator;

    public static SessionFactory getSessionFactory() 
    {
        return sessionFactory;
    }

    private Main() 
    {
        super();
    }

    public static void main(String[] args) 
    {
        log.setLevel(Level.ALL);

        try
        {
            // Create the SessionFactory from hibernate.cfg.xml
            sessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();
            ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        	validator = factory.getValidator();
        }
        catch (Exception ex)
        {
            System.out.println("Verbindung zum Datenbank-Server gescheitert: "+ex.getMessage());
        }
        
        if(sessionFactory == null)
        {
        	log.error("Fatal error while intialising");
        	System.exit(1);
        }
        
        try {
            log.info("Starting \"Filling the DB with testdata!\"");
            Inserts.fillDB();
            log.info("Starting \"Mapping Perstistent Classes and Associations\" (task1)");
            task01();
            log.info("Starting \"Working with JPA-QL and the Hibernate Criteria API\" (task2)");
            task02a();
            task02b();
            task02c();
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void task01() throws ParseException, InterruptedException 
    {
    	
    	
    	Benutzer b1 = new Benutzer();
    	Benutzer b2 = new Benutzer();
    	Zeitkarte t1 = new Zeitkarte();
    	Einzelticket t2 = new Einzelticket();
    	Einzelticket t3 = new Einzelticket();
    	Strecke s1 = new Strecke();
    	Strecke s2 = new Strecke();
    	Strecke s3 = new Strecke();
    	Bahnhof bb1 = new Bahnhof();
    	Bahnhof bb2 = new Bahnhof();
    	Kreditkarte k1 = new Kreditkarte();
    	Reservierung r1 = new Reservierung();
    	Zug z = new Zug();
    	Sonderangebot s = new Sonderangebot();
    	
    	s.setDauer(10);
    	s.setKontingent(1500);
    	s.setPreisNachlass(15.2f);
    	s.setStartZeit(new Date(new Date().getTime()+10000000));

    	b1.seteMail("jklepp@student.tgm.ac.at");
    	b1.setVorName("jakob");
    	b1.setNachName("Klepp");
    	b1.setVerbuchtePraemienMeilen(1337L);
    	b2.seteMail("hell@nothing.uu");
    	b2.setVorName("Peter");
    	b2.setNachName("Silie");
    	b2.setVerbuchtePraemienMeilen(9192949299299L);

    	bb1.setName("Huetteldorf");
    	bb2.setName("Floridsdorf");
    	
    	s1.setStart(bb1);
    	s1.setEnde(bb1);
    	
    	s2.setStart(bb1);
    	s2.setEnde(bb2);
    	
    	s3.setStart(bb1);
    	s3.setEnde(bb1);
    	
    	t1.setZahlung(k1);
    	t1.setStrecke(s1);
    	t1.setTyp(ZeitkartenTyp.MONATSKARTE);
    	t1.setGueltigAb(new Date());
    	
    	t2.setZahlung(k1);
    	t2.setStrecke(s1);
    	t2.setTicketOption(TicketOption.FAHRRAD);
    	
    	t3.setZahlung(k1);
    	t3.setStrecke(s2);
    	t3.setTicketOption(TicketOption.GROSSGEPAECK);
    	
    	z.setStart(bb1);
    	z.setEnde(bb2);
    	z.setFahrradStellplaetze(15);
    	z.setStartZeit(new Date());
    	
    	r1.setDatum(new Date());
    	r1.setPreis(1337);
    	r1.setStrecke(s2);
    	r1.setZahlung(k1);
    	r1.setZug(z);
    	
    	ArrayList<Ticket> ar = new ArrayList<Ticket>();
    	ArrayList<Ticket> ar2 = new ArrayList<Ticket>();
    	ar.add(t1);
    	ar.add(t2);
    	ar2.add(t3);
    	b1.setTickets(ar);
    	
    	b2.setTickets(ar2);
    	r1.setBenutzer(b2);
    	s.setTickets(ar);
    	
    	doValidation(bb1);
    	doValidation(bb2);
    	doValidation(s1);
    	doValidation(s2);
    	doValidation(z);
    	doValidation(s);
    	doValidation(b1);
    	doValidation(b2);
    	
        Session session = getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();
        
        session.save(bb1);
        session.save(bb2);
        session.save(s1);
        session.save(s2);
        session.save(z);
        session.save(r1);
        session.save(t1);
        session.save(t2);
        session.save(t3);
        session.save(s);
        session.save(b1);
        session.save(b2);
        
        tx.commit(); 
    }

    public static void task02a() throws ParseException
    {
    	Session session = getSessionFactory().getCurrentSession();
    	session.beginTransaction();
    	
    	try
    	{
	    	Query query = session.getNamedQuery("getAllReservations")
	        		.setString("emailAddress", "hell");
	    	
	    	List<Reservierung> tickets = (List<Reservierung>)query.list();
	    	Iterator it = tickets.iterator();
	    	while(it.hasNext())
	    	{
	    		Object[] t = (Object[])it.next();
	    		Reservierung r = (Reservierung)t[0];
	    		
	    		System.out.println("Reservierung "+r.getID()+" | Datum: "+r.getDatum()+" | Praem. Meilen: "+r.getPraemienMeilenBonus()+" | Preis: "+r.getPreis()+" | Status: "+r.getStatus());
	    	}
    	}
    	finally
    	{
    		session.getTransaction().commit();
    	}
    }

    public static void task02b() throws ParseException 
    {
    	Session session = getSessionFactory().getCurrentSession();
    	session.beginTransaction();
    	
    	try
    	{
	    	Query query = session.getNamedQuery("getPassengersWithMonthlyPass");
	    	
	    	List<Benutzer> tickets = (List<Benutzer>)query.list();
	    	
	    	Iterator it = tickets.iterator();
	    	while(it.hasNext())
	    	{
	    		Object[] t = (Object[])it.next();
	    		Benutzer b = (Benutzer)t[0];
	    		
	    		System.out.println("Benutzer "+b.getID()+" | Name: "+b.getNachName()+","+b.getVorName()+" | EMail: "+b.geteMail()+ "  | Praem. Meilen: "+b.getVerbuchtePraemienMeilen());
	    	}
    	}
    	finally
    	{
    		session.getTransaction().commit();
    	}
    }

    public static void task02c() throws ParseException 
    {
    	Session session = getSessionFactory().getCurrentSession();
    	session.beginTransaction();
    	
    	try
    	{
	    	Query query = session.getNamedQuery("getConnectionWithoutReservations")
	        		.setInteger("start", 1)
	        		.setInteger("ende", 2);
	    	
	    	List<Ticket> tickets = (List<Ticket>)query.list();
	    	Iterator it = tickets.iterator();
	    	while(it.hasNext())
	    	{
	    		Object[] t = (Object[])it.next();
	    		Ticket gt = (Ticket)t[0];
	    		
	    		System.out.println("Ticket "+gt.getID()+" | Start: "+gt.getStrecke().getStart().getName()+" | Ende: "+gt.getStrecke().getEnde().getName());
	    		if(t[0] instanceof Einzelticket)
	    		{
	    			Einzelticket et = (Einzelticket) t[0];
	    			System.out.println("-- Einzelticket: "+et.getTicketOption().toString());
	    		}
	    		else if(t[0] instanceof Zeitkarte)
	    		{
	    			Zeitkarte zt = (Zeitkarte) t[0];
	    			System.out.println("-- Zeitkarte: "+zt.getGueltigAb()+" | "+zt.getTyp().toString());
	    		}
	    	}
    	}
    	finally
    	{
    		session.getTransaction().commit();
    	}
    }
    
    public static void doValidation (Object t) throws ValidationException
    {
    	Set<ConstraintViolation<Object>> viol = validator.validate(t);
    	Iterator it  = viol.iterator();
    	
    	while(it.hasNext())
    	{
    		ConstraintViolation<Object> v = (ConstraintViolation<Object>)it.next();
    		System.err.println("VIOLATION in "+t.getClass()+" bei: "+v.getInvalidValue()+" | "+v.getMessage());
    		
    		System.exit(1);
    	}
    }
}