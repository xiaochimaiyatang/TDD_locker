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

    public Integer getAvailableBox() {
        return Math.toIntExact(boxes.stream().filter(box -> box.isAvailable()).count());
    }

    public Bag get(Ticket ticket) throws InvalidTicketException {
        if (!ticket.getValid()) {
            throw new InvalidTicketException();
        }
        return boxes.stream()
                .filter(b -> b.getId() == ticket.getBoxId())
                .findFirst()
                .orElseThrow(() -> new InvalidTicketException()).get(ticket);
    }
}
