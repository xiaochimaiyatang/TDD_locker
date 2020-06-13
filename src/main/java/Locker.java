import java.util.List;

public class Locker {
    private List<Box> boxes;

    public Locker(List<Box> boxes) {
        this.boxes = boxes;
    }

    public Ticket save(Bag bag) throws Exception {
        return boxes.stream()
                .filter(b -> b.isAvailable())
                .findFirst()
                .orElseThrow(() -> new Exception("fail to save the bag, no Empty Box"))
                .save(bag);
    }

    public long getAvailableBox() {
        return boxes.stream().filter(box -> box.isAvailable()).count();
    }

    public Bag get(Ticket ticket) throws Exception {
        if (!ticket.getValid()) {
            throw new Exception("fail to get the bag, invalid ticket");
        }
        Box box = boxes.stream()
                .filter(b -> b.getId() == ticket.getBoxId())
                .findFirst()
                .orElseThrow(() -> new Exception("fail to get the bag, wrong ticket"));
        Bag bag = box.get(ticket);
        return bag;
    }
}
