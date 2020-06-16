import java.util.List;
import java.util.Optional;

public class PrimaryRobot {
    private List<Locker> lockers;

    public PrimaryRobot(List<Locker> lockers) {
        this.lockers = lockers;

    }

    public Ticket save(Bag bag) throws Exception {

        Ticket ticket = lockers.stream()
                .filter(locker -> locker.getAvailableBox() != 0)
                .findFirst()
                .orElseThrow(() -> new Exception("fail to save the bag, no Empty Locker"))
                .save(bag);
        return ticket;
    }
}
