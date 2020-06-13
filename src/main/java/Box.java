public class Box {
    private Bag bag;
    private Boolean isAvailable;
    private String id;

    public Box(String id) {
        this.id = id;
        this.isAvailable = true;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public Ticket save(Bag bag) {
        this.bag = bag;
        isAvailable = false;
        return new Ticket(this.id);
    }
}
