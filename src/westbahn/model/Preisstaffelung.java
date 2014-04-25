package westbahn.model;

/**
 * Not really used.
 * 
 * @author Andreas Willinger
 * @author Jakob Klepp
 * @version 20140424
 */
public class Preisstaffelung 
{
	private static Long serialVersionUID;

	@SuppressWarnings("unused")
	private float grossGepaeck = 1.02f;

	private float fahrrad = 1.05f;

	private int zeitkarteWoche = 8;

	private int zeitkarteMonat = 25;

	private int zeitkarteJahr = 250;

	private static Preisstaffelung instance;

	public static Preisstaffelung getInstance() 
	{
		return null;
	}

	private Preisstaffelung() 
	{

	}
}
