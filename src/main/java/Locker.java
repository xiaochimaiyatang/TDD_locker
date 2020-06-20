import exception.InvalidTicketException;
import exception.NoEmptyLockerException;

import java.util.List;

public class Locker {
    private List<Box> boxes;

    public Locker(List<Box> boxes) {
        this.boxes = boxes;
    }

    public Ticket save(Bag bag) throws NoEmptyLockerException {
        return boxes.stream()
                .filter(b -> b.isAvailable())
                .findFirst()
                .orElseThrow(() -> new NoEmptyLockerException())
                .save(bag);
    }

    public Long getAvailableBox() {
        return boxes.stream().filter(box -> box.isAvailable()).count();
    }

    public Bag get(Ticket ticket) throws InvalidTicketException {
        if (!ticket.getValid()) {
            throw new InvalidTicketException();
        }
        Box box = boxes.stream()
                .filter(b -> b.getId() == ticket.getBoxId())
                .findFirst()
                .orElseThrow(() -> new InvalidTicketException());
        Bag bag = box.get(ticket);
        return bag;
    }
}
