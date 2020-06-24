public class Ticket {
    private String boxId;
    private Boolean isValid;
    private TicketType ticketType;
    public Ticket(String id) {
        isValid = true;
        boxId = id;
        ticketType = TicketType.GENERAL;
    }

    public String getBoxId() {
        return boxId;
    }

    public Boolean getValid() {
        return isValid;
    }

    public TicketType getTicketType() {
        return ticketType;
    }

    public void setInvalid() {
        isValid = false;
    }

    public Ticket setTicketType(TicketType ticketType) {
        this.ticketType = ticketType;
        return this;
    }
}
