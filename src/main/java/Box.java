public class Box {
    private Bag bag;
    private Boolean isAvailable;
    private String id;

    public Box(String id) {
        this.id = id;
        this.isAvailable = true;
    }

    public Boolean isAvailable() {
        return isAvailable;
    }

    public Ticket save(Bag bag) {
        this.bag = bag;
        this.isAvailable = false;
        return new Ticket(this.id);
    }

    public Bag get(Ticket ticket) {
        freeBox();
        ticket.setInvalid();
        return bag;
    }

    public String getId() {
        return this.id;
    }

    private void freeBox() {
        isAvailable = true;
    }

}
