public class Ticket {
    private TicketType ticketType;

    public Ticket() {
        ticketType = TicketType.GENERAL;
    }

    public TicketType getTicketType() {
        return ticketType;
    }


    public Ticket setTicketType(TicketType ticketType) {
        this.ticketType = ticketType;
        return this;
    }
}
