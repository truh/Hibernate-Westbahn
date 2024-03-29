########
Westbahn
########

======
Angabe
======

Erstellen Sie von folgendem Modell Persistenzklassen und implementieren Sie
diese mittels der Java Persistence API in einer Dreier-Gruppe.

.. image:: doc/Domainmodel.png
    :width: 100%

Gehen Sie von den folgenden Annahmen aus:

~~~~~~~
1 Suche
~~~~~~~

Die Suche nach Zügen muss auf jeden Fall die Auswahl des Abfahrts- und
Ankunftsortes (nur folgende Bahnhöfe sind möglich: Wien Westbhf, Wien
Hütteldorf, St. Pölten, Amstetten, Linz, Wels, Attnang-Puchheim, Salzburg)
ermöglichen. Dies führt zur Anzeige der möglichen Abfahrten, die zur
Vereinfachung an jedem Tag zur selben Zeit stattfinden. Des weiteren wird auch
die Dauer der Fahrt angezeigt.

In dieser Liste kann nun eine gewünschte Abfahrtszeit ausgewählt werden. Die
Auswahl der Zeit führt zu einer automatischen Weiterleitung zum Ticketshop.

Um sich die Auslastung der reservierten Sitzplätze anzusehen, muss bei dem
Suchlisting noch das Datum ausgewählt werden. Dieses Service steht jedoch nur
registrierten Benutzern zur Verfügung.

~~~~~~~~~~~~
2 Ticketshop
~~~~~~~~~~~~

Man kann Einzeltickets kaufen, Reservierungen für bestimmte Züge durchführen
und Zeitkarten erwerben. Dabei sind folgende Angaben notwendig:

Einzeltickets: Strecke (Abfahrt/Ankunft), Anzahl der Tickets, Optionen
(Fahrrad, Großgepäck)
Reservierung: Strecke (Abfahrt/Ankunft), Art der Reservierung (Sitzplatz,
Fahrrad, Rollstuhlstellplatz), Reisetag und Zug (Datum/Uhrzeit)
Zeitkarte: Strecke, Zeitraum (Wochen- und Monatskarte)

Um einen Überblick zu erhalten, kann der Warenkorb beliebig befüllt und
jederzeit angezeigt werden. Es sind keine Änderungen erlaubt, jedoch können
einzelne Posten wieder gelöscht werden.

Die Funktion „Zur Kassa gehen“ soll die Bezahlung und den Ausdruck der Tickets
sowie die Zusendung per eMail/SMS ermöglichen. Dabei ist für die Bezahlung nur
ein Schein-Service zu verwenden um zum Beispiel eine Kreditkarten- bzw.
Maestrotransaktion zu simulieren.

~~~~~~~~~~~~~~~
3 Prämienmeilen
~~~~~~~~~~~~~~~

Benutzer können sich am System registrieren um getätigte Käufe und
Reservierungen einzusehen. Diese führen nämlich zu Prämienmeilen, die weitere
Vergünstigungen ermöglichen. Um diese beim nächsten Einkauf nützen zu können,
muss sich der Benutzer einloggen und wird beim „Zur Kassa gehen“ gefragt, ob
er die Prämienmeilen für diesen Kauf einlösen möchte.

~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
4 Instant Notification System der Warteliste
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

Der Kunde soll über Änderungen bezüglich seiner Reservierung (Verspätung bzw.
Stornierung) mittels ausgesuchtem Service (eMail bzw. SMS) benachrichtigt
werden. Bei ausgelasteten Zügen soll auch die Möglichkeit einer Anfrage an
reservierte Plätze möglich sein. Dabei kann ein Zuggast um einen Platz
ansuchen, bei entsprechender Änderung einer schon getätigten Reservierung wird
der ansuchende Kunde informiert und es wird automatisch seine Reservierung
angenommen.

~~~~~~~~~~~~~~~~
6 Sonderangebote
~~~~~~~~~~~~~~~~

Für festzulegende Fahrtstrecken soll es ermöglicht werden, dass ein fixes
Kontingent von Tickets (z.b.: 999) zu einem verbilligten Preis (z.b.: 50%
Reduktion) angeboten wird. Diese Angebote haben neben dem Kontingent auch eine
zeitliche Beschränkung. Der Start wird mit Datum und Uhrzeit festgelegt. Die
Dauer wird in Stunden angegeben. Diese Angebote werden automatisch durch Ablauf
der Dauer beendet.


~~~~~~~~
Aufgaben
~~~~~~~~

---------
1 Mapping
---------

(8Pkt)

Schreiben Sie für alle oben definierten Klassen und Relationen entsprechende
Hibernate JPA Implementierungen (javax.persistence.*). Bis auf die Klasse
Reservierung sollen dafür die Annotationen verwendet werden. Die Klasse
Reservierung soll mittels XML Mapping definiert werden.

---------------
2 Named Queries
---------------

(6Pkt)

Schreibe folgende NamedQueries (kein plain SQL und auch keine Inline-Queries)
für das Domänenmodell aus Task1. Die Queries sollen die entsprechenden
Parameter akzeptieren und die gewünschten Typen zurückliefern:

1. Finde alle Reservierungen für einen bestimmten Benutzer, der durch die
   eMail-Adresse definiert wird.
2. Liste alle Benutzer auf, die eine Monatskarte besitzen.
3. Liste alle Tickets für eine bestimmte Strecke aus (durch Anfangs- und
   Endbahnhof definiert), wo keine Reservierungen durchgeführt wurden.

-------------
3 Validierung
-------------

(2Pkt)

Alle Constraints der einzelnen Entitäten sollen verifiziert werden. Hierfür soll die Bean Validation API verwendet werden. Folgende Einschränkungen sollen überprüft werden:

a) Zug und Strecke können nicht denselben Start- und Endbahnhof besitzen.
b) Die eMail des Benutzers soll ein gängiges eMail-Pattern befolgen (http://www.w3.org/TR/2012/CR-html5-20121217/forms.html#valid-e-mail-address).
c) Die Startzeit eines Sonderangebotes kann nicht in der Vergangenheit liegen.
d) Der Name eines Bahnhofs darf nicht kürzer als zwei und nicht länger als 150 Zeichen sein. Sonderzeichen sind bis auf den Bindestrich zu unterbinden.

================
Zeitaufzeichnung
================

+--------------------------+------------+-------+-------+-------+-------------------+
| Arbeitspaket             | Datum      | Start | Ende  | Dauer | Wer               |
+==========================+============+=======+=======+=======+===================+
| import von Astah Code    | 2014-03-28 | 15:10 | 15:40 |  0:30 | Jakob Klepp       |
+--------------------------+------------+-------+-------+-------+-------------------+
| Übernahme der Angabe     | 2014-03-29 | 00:10 | 00:20 |  0:10 | Jakob Klepp       |
+--------------------------+------------+-------+-------+-------+-------------------+
| Mapping mit Annotations  | 2014-04-11 | 13:00 | 14:05 |  1:05 | Jakob Klepp       |
+--------------------------+------------+-------+-------+-------+-------------------+
| Mapping mit Annotations  | 2014-04-11 | 13:00 | 14:05 |  1:05 | Andreas Willinger |
+--------------------------+------------+-------+-------+-------+-------------------+
| Mapping mit Annotations  | 2014-04-11 | 21:00 | 21:30 |  0:30 | Jakob Klepp       |
+--------------------------+------------+-------+-------+-------+-------------------+
| Annotations & Validation | 2014-04-23 | 14:10 | 17:20 |  3:10 | Andreas Willinger |
+--------------------------+------------+-------+-------+-------+-------------------+
| NamedQueries             | 2014-04-23 | 15:10 | 18:20 |  3:10 | Jakob Klepp       |
+--------------------------+------------+-------+-------+-------+-------------------+
| Testdaten erzeugt        | 2014-04-24 | 08:10 | 08:30 |  0:20 | Jakob Klepp       |
+--------------------------+------------+-------+-------+-------+-------------------+
| NamedQueries gefixt      | 2014-04-24 | 08:15 | 10:50 |  2:35 | Andreas Willinger |
+--------------------------+------------+-------+-------+-------+-------------------+
| Code Dokumentation       | 2014-04-24 | 10:50 | 11:20 |  0:30 | Andreas Willinger |
+--------------------------+------------+-------+-------+-------+-------------------+
| Dokumentation            | 2014-04-25 | 15:20 | 16:00 |  0:40 | Jakob Klepp       |
+--------------------------+------------+-------+-------+-------+-------------------+
| Dokumentation            | 2014-04-25 | 15:20 | 16:00 |  0:40 | Andreas Willinger |
+--------------------------+------------+-------+-------+-------+-------------------+

==============================
Datenbank und Benutzer anlegen
==============================

.. code:: sql

    mysql>  CREATE DATABASE westbahn;
    Query OK, 1 row affected (0.08 sec)

    mysql> GRANT ALL ON westbahn.* TO 'westbahnUser'@'localhost'
           IDENTIFIED BY 'westbahnPassword';
    Query OK, 0 rows affected (0.00 sec)


=======
Quellen
=======

.. _1:

[1]  DezSys-07 "Hibernate"
     http://elearning.tgm.ac.at/mod/assign/view.php?id=21803
     zuletzt besucht am: 28.03.2014

.. _2:

[2]  Java EE Tutorial: Persistence
     http://download.oracle.com/javaee/6/tutorial/doc/bnbpy.html
     zuletzt besucht am: 22.04.2014

.. _3:

[3]  Hibernate Community Documentation: Hibernate Annotations
     http://docs.jboss.org/hibernate/stable/annotations/reference/en/html/
     zuletzt besucht am: 22.04.2014

.. _4:

[4]  Hibernate ORM Website
     http://hibernate.org/orm/
     zuletzt besucht am: 22.04.2014

.. header::

    +-------------+-------------------+------------+
    | Titel       | Autor             | Date       |
    +=============+===================+============+
    | ###Title### | Andreas Willinger | 22.04.2014 |
    |             | -- Jakob Klepp    |            |
    +-------------+-------------------+------------+

.. footer::

    ###Page### / ###Total###
