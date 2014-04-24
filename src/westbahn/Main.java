package westbahn;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.ejb.HibernatePersistence;
import westbahn.model.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;


public class Main
{

    private static final Logger log = Logger.getLogger(Main.class);

    static SimpleDateFormat dateForm = new SimpleDateFormat("dd.MM.yyyy");
    static SimpleDateFormat timeForm = new SimpleDateFormat("dd.MM.yyyy mm:hh");

    private static SessionFactory sessionFactory;
    private static EntityManagerFactory entityManagerFactory
            = new HibernatePersistence().createEntityManagerFactory("entityManagerFactory!", new HashMap());

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
            log.info("Starting \"Filling the DB with testdata!\"");
            fillDB(entityManagerFactory.createEntityManager());
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

        ///////////////////////////////////////////////////
        /// Bahnhof                                     ///
        ///////////////////////////////////////////////////
        String[] bahnhofs = new String[]{
                "Wien Westbahnhof",
                "Wien Huetteldorf",
                "St. Poelten Hbf",
                "Amstetten",
                "Linz Hbf",
                "Wels Hbf",
                "Attnang-Puchheim",
                "Salzburg Hbf"
                };

        // in cents
        int[] bahnhofs_preise = new int[] {
                   0,
                 300,
                 690,
                1330,
                1720,
                1880,
                2020,
                2390
        };

        //in minuten
        int[] bahnhofs_dauer = new int[] {
                  0,
                  5,
                 27,
                 52,
                 79,
                 92,
                106,
                150
        };

        //in km
        int[] bahnhofs_weg = new int[] {
                  0,
                  8,
                 61,
                125,
                177,
                193,
                230,
                291
        };

        Bahnhof[] bahnhofs1 = new Bahnhof[bahnhofs.length];

        for(int i = 0; i<bahnhofs.length; i++) {
            Bahnhof bahnhof = new Bahnhof();
            bahnhof.setID((long)i);
            bahnhof.setName(bahnhofs[i]);
            bahnhof.setAbsPreisEntfernung(bahnhofs_preise[i]);
            bahnhof.setAbsZeitEntfernung(bahnhofs_dauer[i]);
            bahnhof.setAbsKmEntfernung(bahnhofs_weg[i]);

            // kopfbahnhof setzen
            if(i == 0 || i == bahnhofs.length-1) {
                bahnhof.setKopfBahnhof(true); //default = false
            }

            bahnhofs1[i] = bahnhof;

            em.persist(bahnhof);
        }

        ///////////////////////////////////////////////////
        /// Benutzer                                    ///
        ///////////////////////////////////////////////////
        String[][] benutzer_data = new String[][]{
                //vorname, nachname, email,                    passwort, sms-nummer
                {"Ponzi", "Ayers", "Ponzi.Ayers@spambog.de", "K7J7UxAGVgr", "7088 9763308312"},
                {"Litterst", "Aragon", "Litterst.Aragon@gmx.at", "BPb5Db+8ewAF", "8709 4981294689"},
                {"Asic", "Allen", "Asic.Allen@gmail.com", "BVLxX9wOhkAC", "5409 8347453559"},
                {"Geoff", "Ball", "Geoff.Ball@gmx.de", "BSf8LdxuoEAI", "6401 5399476378"},
                {"Manteufel", "Avery", "Manteufel.Avery@gmx.de", "w0O737OOSAc", "4411 5644287135"},
                {"Craiola", "Bader", "Craiola.Bader@outlook.com", "BFTeQWMMakAz", "5231 4501621259"},
                {"Bischofer", "Ahmed", "Bischofer.Ahmed@gmx.de", "BLy7v49qwwAJ", "1702 6689900643"},
                {"Herbst", "Barrow", "Herbst.Barrow@yahoo.com", "BMXQg/sAZIAr", "9754 1115009554"},
                {"Fernando", "Bachman", "Fernando.Bachman@student.tgm.ac.at", "ZCpPZvYUOA1", "2092 4014999292"},
                {"Neide", "Adamson", "Neide.Adamson@outlook.com", "Kgl7ynGMIAi", "9899 302380828"},
                {"Franche", "Brandon", "Franche.Brandon@mail.google.com", "U5VXF47bDAQ", "5390 2684621684"},
                {"Kusenberg", "Bouchard", "Kusenberg.Bouchard@gmail.com", "SNo/QLXP1AS", "5888 3564469758"},
                {"Hofer", "Arndt", "Hofer.Arndt@t-online.de", "w6eLb2UNeAC", "8376 9812602429"},
                {"Pfenic", "Best", "Pfenic.Best@student.tgm.ac.at", "916rKuYK4Ab", "6903 9661669244"},
                {"Gadge", "Bruner", "Gadge.Bruner@gmx.de", "ZCdyZCX02AG", "3842 6335908113"},
                {"Kudobe", "Ault", "Kudobe.Ault@outlook.com", "4f/fp1X4WAd", "5738 9432279469"},
                {"Iacobi", "Bradshaw", "Iacobi.Bradshaw@spambog.de", "BCE9a2pqrUAU", "5825 1117958451"},
                {"Stark", "Ashley", "Stark.Ashley@yahoo.com", "cbfRzhqD1Ar", "9206 2725064709"},
                {"Fandel", "Beaudoin", "Fandel.Beaudoin@t-online.de", "Pg/Z4jCFNgR", "2324 6369710454"},
                {"Shaffner", "Beltran", "Shaffner.Beltran@gmx.at", "KVK/3aBy8AA", "6016 8908047972"},
                {"Naiux", "Brant", "Naiux.Brant@gmx.at", "1XncnkeLsAF", "4067 9529453189"},
                {"Kehr", "Adkins", "Kehr.Adkins@spambog.de", "ad/ek9rEqAT", "3449 1649541409"},
                {"Hartman", "Bradley", "Hartman.Bradley@mail.google.com", "SqGWv34D7A1", "1311 3383939902"},
                {"Hartel", "Bonds", "Hartel.Bonds@gmail.com", "cCEIe2oMaAF", "2323 578613504"},
                {"Shaidnagle", "Brink", "Shaidnagle.Brink@outlook.com", "LQiQhczzKA7", "4599 3355440888"},
                {"Burger", "Ashby", "Burger.Ashby@gmail.com", "0H/UBxMtoAg", "1156 6362621420"},
                {"Rudiblon", "Box", "Rudiblon.Box@spambog.de", "7ssnLbD4UAK", "6004 6295229450"},
                {"Trabant", "Barela", "Trabant.Barela@gmx.at", "BPB0LIv9d0Ak", "4822 3316930339"},
                {"Ferderber", "Benjamin", "Ferderber.Benjamin@mail.google.com", "L7jvc62eigN", "1872 9980987843"},
                {"Meurer", "Bruce", "Meurer.Bruce@yahoo.com", "BKcWw8roGkAr", "7842 8087217686"},
                {"Neininger", "Almond", "Neininger.Almond@gmx.de", "Th6pBD++8Ai", "9625 932862900"},
                {"Weltsch", "Arellano", "Weltsch.Arellano@t-online.de", "3uILqwfeIAs", "2471 7248146768"},
                {"Torge", "Bartels", "Torge.Bartels@gmx.de", "wR6FnsAvsAx", "6939 6866797886"},
                {"Kisyma", "Berube", "Kisyma.Berube@mailbox.org", "fESETPD8eAt", "2885 4509810080"},
                {"Leibenstein", "Bayer", "Leibenstein.Bayer@live.de", "BDxX+pn20oAf", "1578 7759772080"},
                {"Pruss", "Betz", "Pruss.Betz@live.de", "w4dD9AhSCAr", "5839 9283941415"},
                {"Sautermeister", "Askew", "Sautermeister.Askew@t-online.de", "qh/9BNG/WAq", "7010 7148944595"},
                {"Guntermann", "Bagley", "Guntermann.Bagley@mail.google.com", "PpXPZx8TOAf", "5424 7329553607"},
                {"Leibenstein", "Bagwell", "Leibenstein.Bagwell@outlook.com", "puQVbLpPCAc", "1512 3292249648"},
                {"Nagler", "Ammons", "Nagler.Ammons@gmx.at", "Purd3O+Ulgr", "3201 8715052552"},
                {"Adolf", "Banks", "Adolf.Banks@mail.google.com", "BIU6FCJ9s0AK", "5850 6814217341"},
                {"Strempel", "Banuelos", "Strempel.Banuelos@gmx.at", "yKl9Y998kAt", "4348 531626184"},
                {"Alphonso", "Bernier", "Alphonso.Bernier@yahoo.com", "XR0IlPSBQAF", "4523 9324903462"},
                {"Finarolli", "Boatwright", "Finarolli.Boatwright@gmail.com", "z8J0808ZwAc", "5138 5579777323"},
                {"Vomhof", "Boles", "Vomhof.Boles@spambog.de", "taUuBzvhCAv", "3046 3139782279"},
                {"Ager", "Broadway", "Ager.Broadway@spambog.de", "l7cWTDNAYAa", "4121 966168800"},
                {"Walther", "Briscoe", "Walther.Briscoe@gmail.com", "qeeFzL8hOA2", "5671 6257456278"},
                {"Hektor", "Beam", "Hektor.Beam@mailbox.org", "Yquydf422AA", "7992 4369246628"},
                {"Albert", "Boehm", "Albert.Boehm@live.de", "+84G4IOnyAF", "3369 7486928938"},
                {"Yascha", "Alvarez", "Yascha.Alvarez@t-online.de", "YVlomCgyUAf", "3826 4852554440"},
                {"Raphael", "Antoine", "Raphael.Antoine@outlook.com", "VxKdCFiFBAe", "4742 2908225730"},
                {"Richard", "Blanchard", "Richard.Blanchard@spambog.de", "/JR7+9kKUAD", "7935 4212714031"},
                {"Stefanie", "Alfaro", "Stefanie.Alfaro@t-online.de", "cJhyiFlsKAC", "8160 2975678521"},
                {"Muench", "Amaral", "Muench.Amaral@outlook.com", "qaJWj3i7UA8", "8011 8064414216"},
                {"Abelard", "Boatwright", "Abelard.Boatwright@mail.google.com", "hpw1yZnwSAi", "4313 9075530786"},
                {"", "Aiello", ".Aiello@gmail.com", "4WKIZBE7AA1", "3788 2889077756"},
                {"Marilette", "Beverly", "Marilette.Beverly@mail.google.com", "6uIFjNbUgAR", "7605 743951833"},
                {"Kisyma", "Berg", "Kisyma.Berg@mailbox.org", "mlSAYdavOAG", "3574 2269502356"},
                {"Magdalene", "Baird", "Magdalene.Baird@gmail.com", "BMhxd/sdDQAF", "2577 9318326859"},
                {"Brukner", "Armijo", "Brukner.Armijo@student.tgm.ac.at", "me7O0IMPIA+", "3429 7905264123"},
                {"Stoper", "Barnett", "Stoper.Barnett@gmail.com", "BUNYO2zofgA+", "8014 5626481635"},
                {"Idita", "Ammons", "Idita.Ammons@live.de", "+oknGYEqmA1", "6855 869092400"},
                {"Hinrich", "Alonzo", "Hinrich.Alonzo@yahoo.com", "BDr+1z152wA0", "1243 6459351073"},
                {"Urich", "Banuelos", "Urich.Banuelos@gmx.at", "WT5/Od+MFAV", "6151 5630179758"},
                {"Pretscherer", "Brandenburg", "Pretscherer.Brandenburg@mailbox.org", "BOjwmQ1oPcAF", "5047 414074696"},
                {"Atoulf", "Barrios", "Atoulf.Barrios@gmx.de", "dLVXuoAhHA/", "1735 4923406993"},
                {"Hanilori", "Acuna", "Hanilori.Acuna@spambog.de", "6bMDbzDFyAd", "8344 7846488582"},
                {"Wachowitz", "Abraham", "Wachowitz.Abraham@gmail.com", "BR/vIov1pUAz", "7537 617285890"},
                {"Heine", "Baines", "Heine.Baines@mail.google.com", "v9eyLwaEsAl", "8049 204868710"},
                {"Verner", "Benjamin", "Verner.Benjamin@outlook.com", "htInE2EM8AF", "7366 3764420003"},
                {"Pfeiller", "Billingsley", "Pfeiller.Billingsley@live.de", "BJ6kOfOQ9IA/", "3927 8538767883"},
                {"Schikotki", "Baylor", "Schikotki.Baylor@gmx.at", "6aKA33uIsA8", "6707 3115183405"},
                {"Deblond", "Ammons", "Deblond.Ammons@student.tgm.ac.at", "RuP6rwGyQAA", "7442 1865622583"},
                {"Kloble", "Anglin", "Kloble.Anglin@yahoo.com", "+b4evn/lwAx", "4702 2786661742"},
                {"Seling", "Benedict", "Seling.Benedict@mail.google.com", "MmE+UMJLigN", "8528 9568696630"},
                {"Pannaporn", "Allan", "Pannaporn.Allan@live.de", "68Lq/8chyAt", "1897 3412586398"},
                {"Dieden", "Borrego", "Dieden.Borrego@t-online.de", "BUZ8Tc8Qc8AZ", "9242 4485533013"},
                {"Fuss", "Bordelon", "Fuss.Bordelon@student.tgm.ac.at", "v83sc33aYA0", "1621 2266355905"},
                {"Lindekugel", "Barth", "Lindekugel.Barth@outlook.com", "BPyXoZnZbYA2", "4457 1738738175"},
                {"Kelting", "Bland", "Kelting.Bland@live.de", "uvmg1K5qAAy", "1571 1512182721"},
                {"Leibenstein", "Alcala", "Leibenstein.Alcala@live.de", "BQqF1aTgI0Av", "4189 1207606143"},
                {"Hafermann", "Breaux", "Hafermann.Breaux@student.tgm.ac.at", "NM/65/skKAl", "9011 9295848029"},
                {"Meurer", "Bannister", "Meurer.Bannister@gmx.de", "ulkHgRxeAA3", "4337 4595004289"},
                {"Natanya", "Bowers", "Natanya.Bowers@yahoo.com", "+RYMv4qCyAP", "1217 6630360737"},
                {"Hegwer", "Batten", "Hegwer.Batten@t-online.de", "BOrpmwaUMcAI", "6064 7059432154"},
                {"Wald", "Beltran", "Wald.Beltran@yahoo.com", "93mEXjadoAc", "2057 5510943456"},
                {"Fernando", "Blais", "Fernando.Blais@mailbox.org", "oaG9np+82A5", "5423 7630614262"},
                {"Bilshause", "Berube", "Bilshause.Berube@student.tgm.ac.at", "61wmy7jpKAv", "6749 3173925049"},
                {"Anselm", "Barnette", "Anselm.Barnette@gmx.at", "p+t2joHNqAj", "1050 8314520849"},
                {"Bors", "Boland", "Bors.Boland@gmx.de", "u7RZZtkMWA9", "7069 4529689613"},
                {"Huterstein", "Broyles", "Huterstein.Broyles@gmx.at", "viZqvsXSgAL", "5521 1701823708"},
                {"Acker", "Arias", "Acker.Arias@gmx.de", "N2WyuL5ldA/", "5604 5766615047"},
                {"Dirrmann", "Artis", "Dirrmann.Artis@mail.google.com", "Vvk4rvAryAs", "5867 2021580101"},
                {"Tatzkow", "Becnel", "Tatzkow.Becnel@spambog.de", "axiYx31srA2", "2781 5536232429"},
                {"Brustiuc", "Brittain", "Brustiuc.Brittain@live.de", "9m0fXS8EgAL", "1689 5563835876"},
                {"Lincks", "Bankston", "Lincks.Bankston@gmx.at", "cR4b2ZgKGAb", "4978 3366933982"},
                {"Seserman", "Akers", "Seserman.Akers@spambog.de", "BR60/TFIuMA3", "1740 39656611"},
                {"Magdalene", "Albright", "Magdalene.Albright@yahoo.com", "BKaM0dxsj0Ai", "5807 2970005010"},
                {"Schoeman", "Barlow", "Schoeman.Barlow@yahoo.com", "BWrmaloHUEAj", "1006 913309573"},
                {"Reiner", "Boles", "Reiner.Boles@spambog.de", "bcPzbPH5fAH", "9644 5521936685"}
        };

        for(int i=0; i<benutzer_data.length; i++){
            String[] b_data = benutzer_data[i];
            Benutzer benutzer = new Benutzer();
            benutzer.setID((long)i);
            benutzer.setVorName(b_data[0]);
            benutzer.setNachName(b_data[1]);
            benutzer.seteMail(b_data[2]);
            benutzer.setPasswort(b_data[3]);
            benutzer.setSmsNummer(b_data[4]);

            em.persist(benutzer);
        }

        ///////////////////////////////////////////////////
        /// Zug                                         ///
        ///////////////////////////////////////////////////
        long zugId=0L;
        Zug zug = new Zug();
        zug.setID(zugId++);
        zug.setStartZeit(new Date(2014, 4, 23, 8, 50));
        zug.setStart(bahnhofs1[0]);
        zug.setEnde(bahnhofs1[7]);
        em.persist(zug);

        zug = new Zug();
        zug.setID(zugId++);
        zug.setStartZeit(new Date(2013, 5, 23, 9, 50));
        zug.setStart(bahnhofs1[7]);
        zug.setEnde(bahnhofs1[0]);
        em.persist(zug);

        zug = new Zug();
        zug.setID(zugId++);
        zug.setStartZeit(new Date(2012, 6, 23, 10, 50));
        zug.setStart(bahnhofs1[0]);
        zug.setEnde(bahnhofs1[7]);
        em.persist(zug);

        zug = new Zug();
        zug.setID(zugId++);
        zug.setStartZeit(new Date(2011, 7, 23, 11, 50));
        zug.setStart(bahnhofs1[7]);
        zug.setEnde(bahnhofs1[0]);
        em.persist(zug);
    }

    public static void task01() throws ParseException, InterruptedException 
    {
    	Benutzer b1 = new Benutzer();
    	Zeitkarte t1 = new Zeitkarte();
    	Strecke s1 = new Strecke();
    	Bahnhof bb1 = new Bahnhof();
    	Bahnhof bb2 = new Bahnhof();
    	Kreditkarte k1 = new Kreditkarte();

    	b1.seteMail("stuff@stuff");

    	bb1.setName("Huetteldorf");
    	bb2.setName("Floridsdorf");
    	
    	s1.setStart(bb1);
    	s1.setEnde(bb1);

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