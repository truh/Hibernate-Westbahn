package westbahn.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Strecke {

    @Id
	private Long ID;

	private Bahnhof start;

	private Bahnhof bahnhof;

	private Bahnhof ende;

}
