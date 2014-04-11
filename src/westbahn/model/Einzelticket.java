package westbahn.model;

public class Einzelticket extends Ticket {

	private TicketOption ticketOption;

    public TicketOption getTicketOption() {
        return ticketOption;
    }

    public void setTicketOption(TicketOption ticketOption) {
        this.ticketOption = ticketOption;
    }
}
