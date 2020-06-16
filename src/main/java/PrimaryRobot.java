import java.util.List;
import java.util.Optional;

public class PrimaryRobot {
    private List<Locker> lockers;

    public PrimaryRobot(List<Locker> lockers) {
        this.lockers = lockers;

    }

    public Ticket save(Bag bag) throws Exception {
        Optional<Locker> firstNotFullLocker = lockers.stream().filter(locker -> locker.getAvailableBox()!=0).findFirst();
        Ticket ticket = firstNotFullLocker.get().save(bag);
        return ticket;
    }
}
