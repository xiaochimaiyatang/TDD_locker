import java.util.List;
import java.util.Optional;

public class Locker {
    private List<Box> boxes;

    public Locker(List<Box> boxes) {
        this.boxes = boxes;
    }

    public Ticket save(Bag bag) throws Exception {
        Optional<Box> boxOptional = boxes.stream().filter(box -> box.isAvailable()).findFirst();
        if (boxOptional.isPresent()) {
            Box box1 = boxOptional.get();
            System.out.println(box1);
            Ticket ticket = box1.save(bag);
            return ticket;
        }
        throw  new Exception("fail to save the bag, no Empty Box");
    }

    public int getAvailableBox() {
        int count = (int) boxes.stream().filter(box -> box.isAvailable()).count();
        return count;
    }

    public Bag get(Ticket ticket) throws Exception {
        Optional<Box> optionalBox = boxes.stream().filter(box -> box.getId() == ticket.getBoxId()).findFirst();
        if (optionalBox.isPresent()) {
            Box box=optionalBox.get();
            Bag bag = box.get();
            return bag;
        }
        throw  new Exception("fail to get the bag, wrong ticket");
    }
}
