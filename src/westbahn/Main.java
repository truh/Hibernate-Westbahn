package westbahn;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.hibernate.*;
import org.hibernate.cfg.*;

import westbahn.model.Benutzer;
import westbahn.model.Kreditkarte;
import westbahn.model.Ticket;
import westbahn.model.Zeitkarte;

import javax.persistence.EntityManager;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;


public class Main
{

    private static final Logger log = Logger.getLogger(Main.class);

    static SimpleDateFormat dateForm = new SimpleDateFormat("dd.MM.yyyy");
    static SimpleDateFormat timeForm = new SimpleDateFormat("dd.MM.yyyy mm:hh");

    private static final SessionFactory sessionFactory;

    static {
        try {
            // Create the SessionFactory from hibernate.cfg.xml
            sessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();
        } catch (Throwable ex) {
            // Make sure you log the exception, as it might be swallowed
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    private Main() {
        super();
    }

    public static void main(String[] args) {
        log.setLevel(Level.ALL);
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

    public static void task01() throws ParseException, InterruptedException {
    	Benutzer b1 = new Benutzer();
    	b1.setID(1337L);
    	Zeitkarte t1 = new Zeitkarte();
    	t1.setID(1300L);
    	t1.setZahlung(new Kreditkarte());
    	b1.seteMail("stuff@stuff.com");
    	
    	ArrayList<Ticket> ar = new ArrayList<Ticket>();
    	ar.add(t1);
    	b1.setTickets(ar);

        Session session = getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();
        //session.save(xxxx);
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