package westbahn.model;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Inheritance;

/**
 * Einzelticket Entity
 * 
 * @author Andreas Willinger
 * @author Jakob Klepp
 * @version 20140424
 */
@Entity
@Inheritance
public class Einzelticket extends Ticket 
{
    @Enumerated(value = EnumType.STRING)
	private TicketOption ticketOption;

    public TicketOption getTicketOption() 
    {
        return ticketOption;
    }

    public void setTicketOption(TicketOption ticketOption) 
    {
        this.ticketOption = ticketOption;
    }
}
