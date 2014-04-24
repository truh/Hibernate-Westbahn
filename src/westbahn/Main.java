package westbahn;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.hibernate.*;
import org.hibernate.cfg.*;

import westbahn.model.*;
import javax.persistence.EntityManager;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;


public class Main
{

    private static final Logger log = Logger.getLogger(Main.class);

    static SimpleDateFormat dateForm = new SimpleDateFormat("dd.MM.yyyy");
    static SimpleDateFormat timeForm = new SimpleDateFormat("dd.MM.yyyy mm:hh");

    private static SessionFactory sessionFactory;

    static 
    {
        try 
        {
            // Create the SessionFactory from hibernate.cfg.xml
            sessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();
        }
        catch (Exception ex) 
        {
            System.out.println("Verbindung zum Datenbank-Server gescheitert: "+ex.getMessage());
        }
    }

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
        
        if(sessionFactory == null)
        {
        	log.error("Fatal error while intialising");
        	System.exit(1);
        }
        
        try {
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

    public static void fillDB(EntityManager em) throws ParseException {
        // dateForm.parse("01.01.1930")
    }

    public static void task01() throws ParseException, InterruptedException 
    {
    	Benutzer b1 = new Benutzer();
    	Zeitkarte t1 = new Zeitkarte();
    	Strecke s1 = new Strecke();
    	Bahnhof bb1 = new Bahnhof();
    	Bahnhof bb2 = new Bahnhof();
    	Kreditkarte k1 = new Kreditkarte();
    	
    	b1.setID(1337L);
    	b1.seteMail("stuff@stuff");
    	
    	bb1.setName("hallo");
    	bb1.setID(0L);
    	bb2.setName("tschuess");
    	bb2.setID(1L);
    	
    	s1.setStart(bb1);
    	s1.setEnde(bb1);
    	s1.setID(1L);
    	
    	t1.setID(1300L);
    	t1.setZahlung(k1);
    	t1.setStrecke(s1);
    	t1.setTyp(ZeitkartenTyp.MONATSKARTE);
    	
    	ArrayList<Ticket> ar = new ArrayList<Ticket>();
    	ar.add(t1);
    	b1.setTickets(ar);

        Session session = getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();
        
        session.save(bb1);
        session.save(bb2);
        session.save(s1);
        session.save(t1);
        session.save(b1);
        
        tx.commit();
    }

    public static void task02a() throws ParseException {
    }

    public static void task02b() throws ParseException {
    }

    public static void task02c() throws ParseException {
    }

}